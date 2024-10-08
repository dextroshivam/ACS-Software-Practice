package com.acs.acs.Services;

import com.acs.acs.DTO.RequestDTO.OrderRequest.OMSProductDetailsDTO;
import com.acs.acs.DTO.RequestDTO.OrderRequest.OMSRequestDTO;
import com.acs.acs.ENUM.OrderStatus;
import com.acs.acs.ENUM.ServiceType;
import com.acs.acs.Enitities.*;
import com.acs.acs.Repository.*;
import com.acs.acs.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OMSInfoService {

  @Autowired private OMSInfoRepository omsInfoRepository;
  @Autowired private OMSOrdersItemsRepository omsOrdersItemsRepository;
  @Autowired private WarehouseReceivedItemsRepository warehouseReceivedItemsRepository;
  @Autowired private WarehouseOrdersInfoRepository warehouseOrdersInfoRepository;
  @Autowired private ProductRepository productRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired private WarehouseOrdersItemsRepository warehouseOrdersItemsRepository;
  @Autowired private AddressRepository addressRepository;
  @Autowired private PartnersRepository partnersRepository;

  public String addOrderDetailsInOMS(OMSRequestDTO omsRequestDTO) {
    /* -- verify the address first
     * 1. save omsinfo - done
     * 2. save omsunit (if the product is present in the repo)
     * 3. check quantity of product
     * 4. if quantity<present -> save in wms - update the quantity
     * 5. if quantity>present
     *       -> check if partial order is on
     *           -> ON- Split order in two, main and s1
     *                -  change the id of unfullfilled order with an s1 added
     *                -  make the state to back order--> save to cil
     *                -  save the fullfilled order in wms - update the quantity
     *           -> OFF- Create a back order of full amount - save in CIL with reason
     * */

    // create oms without setting reason
    OMSOrdersInfo savedOmsOrdersInfo;

    /* Check address */
    Optional<Address> address =
        addressRepository.findByCityIdAndStateIdAndCountyIdAndZipCode(
            omsRequestDTO.getShipToCityId(),
            omsRequestDTO.getShipToStateId(),
            omsRequestDTO.getShipToCountryId(),
            omsRequestDTO.getShipToZipCode());

    //    Configure partners here
    Optional<Partners> partner = partnersRepository.findByCustomerId(omsRequestDTO.getCustomerId());


    if (!address.isPresent()) {
      savedOmsOrdersInfo =
          saveOmsOrdersInfo(
              omsRequestDTO.getOrderNumber(), omsRequestDTO.getCustomerId(), "", null,partner, address);
    } else {
      savedOmsOrdersInfo =
          saveOmsOrdersInfo(
              omsRequestDTO.getOrderNumber(), omsRequestDTO.getCustomerId(), "", null,partner, address);
    }

    String responseString = "";

    boolean isErroredOrdered = false;

    // create oms units
    List<OMSProductDetailsDTO> productDetails = omsRequestDTO.getProductDetails();
    boolean isWarehouseOrdersInfoCreated = false;
    WarehouseOrdersInfo savedWarehouseOrdersInfo = new WarehouseOrdersInfo();
    for (OMSProductDetailsDTO omsProductDetailsDTO : productDetails) {
      // check if the product is present
      Optional<WarehouseReceivedItems> warehouseReceivedItems =
          warehouseReceivedItemsRepository.findOneByProductId(omsProductDetailsDTO.getProductId());

      if (warehouseReceivedItems.isPresent()) {
        // check if the quantity is available
        Long orderQuantity = omsProductDetailsDTO.getQuantity();
        Long presentQuantity = warehouseReceivedItems.get().getQuantity();

        if (orderQuantity > presentQuantity) {
          isErroredOrdered = true;
          /* Order Quantity is more  */
          // check if partial order is on
          if (presentQuantity == 0) {
            // create back order for full amount if the prodcut is not present
            OMSOrdersInfo backOrderedOMSOrdersInfo =
                saveOmsOrdersInfo(
                    omsRequestDTO.getOrderNumber(),
                    warehouseReceivedItems.get().getCustomerId(),
                    "BACK_ORDER",
                    OrderStatus.BACKORDER,partner,address);

            // save omsunit with left over quantity with back order status in cil
            OMSOrdersItems saveBackOrderOMSOrdersItems =
                saveOMSOrdersItems(
                    omsProductDetailsDTO,
                    backOrderedOMSOrdersInfo,
                    orderQuantity,
                    OrderStatus.BACKORDER);
            responseString +=
                "Back order created for Product Id "
                    + omsProductDetailsDTO.getProductId()
                    + " for Quantity : "
                    + (orderQuantity)
                    + ".\n";
          } else if (customerRepository
              .findById(warehouseReceivedItems.get().getCustomerId())
              .get()
              .getIsPartialOrder()) {
            /* Partial Order is true */
            // save oms unit with present quantity with created status
            OMSOrdersItems savedOMSOrdersItems =
                saveOMSOrdersItems(
                    omsProductDetailsDTO,
                    savedOmsOrdersInfo,
                    (long) presentQuantity,
                    OrderStatus.CREATED);
            // save in wms info and units and deduct the quantity in inventory

            if (!isWarehouseOrdersInfoCreated) {
              isWarehouseOrdersInfoCreated = true;
              savedWarehouseOrdersInfo =
                  saveWarehouseOrdersInfo(
                      savedOmsOrdersInfo.getOrderNumber(),
                      warehouseReceivedItems.get().getCustomerId(),
                      OrderStatus.CREATED,partner,address);
            }
            // decrease quantity
            warehouseReceivedItems.get().setQuantity((long) 0);
            // save wh order units
            WarehouseOrdersItems savedWarehouseOrdersItems =
                saveWarehouseOrdersItems(
                    warehouseReceivedItems.get().getProductId(),
                    presentQuantity,
                    savedWarehouseOrdersInfo.getId());

            // save oms info of back order with s1 id and back order status
            OMSOrdersInfo backOrderedOMSOrdersInfo =
                saveOmsOrdersInfo(
                    omsRequestDTO.getOrderNumber() + "S1",
                    warehouseReceivedItems.get().getCustomerId(),
                    "BACK_ORDER",
                    OrderStatus.BACKORDER,partner,address);

            // save omsunit with left over quantity with back order status in cil
            OMSOrdersItems saveBackOrderOMSOrdersItems =
                saveOMSOrdersItems(
                    omsProductDetailsDTO,
                    backOrderedOMSOrdersInfo,
                    orderQuantity - presentQuantity,
                    OrderStatus.BACKORDER);
            responseString +=
                "Back order created for Product Id "
                    + omsProductDetailsDTO.getProductId()
                    + " for Quantity : "
                    + (orderQuantity - presentQuantity)
                    + ".\n";
          } else {
            /* Partial order is false*/
            // save oms info of back order with back order status
            OMSOrdersInfo backOrderedOMSOrdersInfo =
                saveOmsOrdersInfo(
                    omsRequestDTO.getOrderNumber(),
                    warehouseReceivedItems.get().getCustomerId(),
                    "BACK_ORDER",
                    OrderStatus.BACKORDER,partner,address);

            // save omsunit with left over quantity with back order status in cil
            OMSOrdersItems saveBackOrderOMSOrdersItems =
                saveOMSOrdersItems(
                    omsProductDetailsDTO,
                    backOrderedOMSOrdersInfo,
                    orderQuantity,
                    OrderStatus.BACKORDER);
            responseString +=
                "Back order created for Product Id "
                    + omsProductDetailsDTO.getProductId()
                    + " for Quantity : "
                    + (orderQuantity)
                    + ".\n";
          }

        } else {

          /* Order Quantity is less than present -> good to go */
          OMSOrdersItems savedOMSOrdersItems =
              saveOMSOrdersItems(
                  omsProductDetailsDTO, savedOmsOrdersInfo, orderQuantity, OrderStatus.CREATED);
          // save in wms info and units and deduct the quantity in inventory
          if (!isWarehouseOrdersInfoCreated) {
            isWarehouseOrdersInfoCreated = true;
            savedWarehouseOrdersInfo =
                saveWarehouseOrdersInfo(
                    savedOmsOrdersInfo.getOrderNumber(),
                    warehouseReceivedItems.get().getCustomerId(),
                    OrderStatus.CREATED,partner,address);
          }

          // decrease quantity
          warehouseReceivedItems.get().setQuantity(presentQuantity - orderQuantity);
          // save wh order units
          WarehouseOrdersItems savedWarehouseOrdersItems =
              saveWarehouseOrdersItems(
                  warehouseReceivedItems.get().getProductId(),
                  orderQuantity,
                  savedWarehouseOrdersInfo.getId());
        }
      } else {
        isErroredOrdered = true;
        responseString +=
            "Product with product id " + omsProductDetailsDTO.getProductId() + " does not exist\n";
      }
    }

    if (isErroredOrdered) {
      System.out.println(isErroredOrdered);
      //            savedOmsOrdersInfo.setReason("One or more products are not valid");
      savedOmsOrdersInfo.setOrderStatus(OrderStatus.INITIATED);
      savedOmsOrdersInfo.setReason("One or more products have a problem");
      omsInfoRepository.save(savedOmsOrdersInfo);
      return responseString;
    }
    return "";
  }

  /* Helper functions */
  private WarehouseOrdersItems saveWarehouseOrdersItems(
      Long productId, Long productQuantity, Long warehouseOrderInfoId) {
    //        private Long productId;
    //        private Long productQuantity;
    //        private Long warehouseOrderInfoId;
    WarehouseOrdersItems warehouseOrdersItems = new WarehouseOrdersItems();
    warehouseOrdersItems.setProductId(productId);
    warehouseOrdersItems.setProductQuantity(productQuantity);
    warehouseOrdersItems.setWarehouseOrderInfoId(warehouseOrderInfoId);
    return warehouseOrdersItemsRepository.save(warehouseOrdersItems);
  }

  private OMSOrdersInfo saveOmsOrdersInfo(
      String orderNumber, Long customerId, String reason, OrderStatus orderStatus,
      Optional<Partners> partner,Optional<Address> address) {
    OMSOrdersInfo omsOrdersInfo = new OMSOrdersInfo();
    //        private String orderNumber;
    //        private Long clientId;
    //        private String reason;
    omsOrdersInfo.setCustomerId(customerId);
    omsOrdersInfo.setOrderNumber(orderNumber);
    omsOrdersInfo.setReason(reason);
    omsOrdersInfo.setOrderStatus(orderStatus);
    // set partner
    if(partner.isPresent() && partner.get().getIs_default()){
      omsOrdersInfo.setCarrierId(partner.get().getId());
      omsOrdersInfo.setCarrierName(partner.get().getName());
      omsOrdersInfo.setServiceType(partner.get().getServiceType());
    }
    omsOrdersInfo.setShipToCityId(address.get().getCityId());
    omsOrdersInfo.setShipToStateId(address.get().getStateId());
    omsOrdersInfo.setShipToCountryId(address.get().getCountyId());
    omsOrdersInfo.setShipToZipCode(address.get().getZipCode());
    return omsInfoRepository.save(omsOrdersInfo);
  }

  private OMSOrdersItems saveOMSOrdersItems(
      OMSProductDetailsDTO omsProductDetailsDTO,
      OMSOrdersInfo savedOmsOrdersInfo,
      Long quantity,
      OrderStatus orderStatus) {
    OMSOrdersItems omsOrdersItems = new OMSOrdersItems();
    //            private Long productId;
    //            private Long productQuantity;
    //            private OrderStatus orderStatus;
    //            Long omsOrdersInfoId;
    omsOrdersItems.setProductId(omsProductDetailsDTO.getProductId());
    omsOrdersItems.setProductQuantity(quantity);
    omsOrdersItems.setOrderStatus(orderStatus);
    omsOrdersItems.setOmsOrdersInfoId(savedOmsOrdersInfo.getId());
    return omsOrdersItemsRepository.save(omsOrdersItems);
  }

  private WarehouseOrdersInfo saveWarehouseOrdersInfo(
      String orderNumber, Long customerId, OrderStatus orderStatus,Optional<Partners> partner,Optional<Address> address) {

    //        private String orderNumber;
    //        private Long clientId;
    //        private OrderStatus orderStatus;
    WarehouseOrdersInfo warehouseOrdersInfo = new WarehouseOrdersInfo();
    warehouseOrdersInfo.setOrderNumber(orderNumber);
    warehouseOrdersInfo.setCustomerId(customerId);
    warehouseOrdersInfo.setOrderStatus(orderStatus);

    // set partner
    if(partner.isPresent() && partner.get().getIs_default()){
      warehouseOrdersInfo.setCarrierId(partner.get().getId());
      warehouseOrdersInfo.setCarrierName(partner.get().getName());
      warehouseOrdersInfo.setServiceType(partner.get().getServiceType());
    }
    warehouseOrdersInfo.setShipToCityId(address.get().getCityId());
    warehouseOrdersInfo.setShipToStateId(address.get().getStateId());
    warehouseOrdersInfo.setShipToCountryId(address.get().getCountyId());
    warehouseOrdersInfo.setShipToZipCode(address.get().getZipCode());


    return warehouseOrdersInfoRepository.save(warehouseOrdersInfo);
  }
}
