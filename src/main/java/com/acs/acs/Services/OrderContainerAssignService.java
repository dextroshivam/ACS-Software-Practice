package com.acs.acs.Services;

import com.acs.acs.DTO.RequestDTO.OrderContainerRequest.OrderContainerAssignRequestDTO;
import com.acs.acs.DTO.RequestDTO.OrderContainerRequest.ProductAssignContainerDTO;
import com.acs.acs.DTO.RequestDTO.OrderContainerRequest.ProductContainerQuantityDTO;
import com.acs.acs.ENUM.OrderStatus;
import com.acs.acs.Enitities.Container;
import com.acs.acs.Enitities.OrderContainerAssign;
import com.acs.acs.Enitities.WarehouseOrdersInfo;
import com.acs.acs.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderContainerAssignService {
  @Autowired OrderContainerAssignRespository orderContainerAssignRespository;
  @Autowired private WarehouseOrdersInfoRepository warehouseOrdersInfoRepository;
  @Autowired private WarehouseOrdersItemsRepository warehouseOrdersItemsRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired private ContainerRepository containerRepository;

  public String assignContainer(OrderContainerAssignRequestDTO orderContainerAssignRequestDTO) {
    /*
     * 1. change status of order to picked
     * 2. validation of quantity of product in containers
     * 3. save the data
     * 4. get client id
     */
    StringBuilder response = new StringBuilder();
    HashSet<Long> containerIdSet = new HashSet<>();
    /********************  Get static data member's value **********************/
    String orderNumber = orderContainerAssignRequestDTO.getOrderNumber();
    WarehouseOrdersInfo warehouseOrdersInfo =
        warehouseOrdersInfoRepository.findByOrderNumber(orderNumber).get();
    Long warehouseOrdersInfoId = warehouseOrdersInfo.getId();
    Long customerId = warehouseOrdersInfo.getCustomerId();
    String pickerName = orderContainerAssignRequestDTO.getPickerName();
    /******************** create OrderContainerAssign **********************/
    List<OrderContainerAssign> responseList = new ArrayList<>();

    // List of product details
    List<ProductAssignContainerDTO> productRequestsLists =
        orderContainerAssignRequestDTO.getProductRequestsLists();

    List<ProductAssignContainerDTO> successProductList = new ArrayList<>();
    // validate quantity first and create a success product list
    for (ProductAssignContainerDTO productAssignContainerDTO : productRequestsLists) {
      Long productId = productAssignContainerDTO.getProductId();
      Long presentOrderQuantity =
          warehouseOrdersItemsRepository
              .findByProductIdAndWarehouseOrderInfoId(productId, warehouseOrdersInfoId)
              .getProductQuantity();
      // list of container specification
      List<ProductContainerQuantityDTO> productContainerQuantityDTOList =
          productAssignContainerDTO.getProductContainerQuantityDTOList();
      Long currentOrderQuantity = 0L;
      for (ProductContainerQuantityDTO productContainerQuantityDTO :
          productContainerQuantityDTOList) {
        // sum up the quantities
        currentOrderQuantity += productContainerQuantityDTO.getQuantity();
      }
      // validate the quantity
      if (presentOrderQuantity != currentOrderQuantity) {
        response
            .append("Product with product Id : ")
            .append(productId)
            .append(" has indefinite quantity in container");
        return response.toString();
      }
      successProductList.add(productAssignContainerDTO);
    }
    // save in db and set status
    for (ProductAssignContainerDTO productAssignContainerDTO : successProductList) {
      Long productId = productAssignContainerDTO.getProductId();

      for (ProductContainerQuantityDTO productContainerQuantityDTO :
          productAssignContainerDTO.getProductContainerQuantityDTOList()) {
        OrderContainerAssign orderContainerAssign = new OrderContainerAssign();
        //            private String orderNumber; - static
        //            private String pickerName; - static
        //            private Long customerId; - static
        //            private Long productId;
        //            private Long containerId;
        //            private Integer quantity;
        //            private LocalDateTime assignedOn; - static
        //            private OrderStatus status; - validated
        //       ** static fields
        orderContainerAssign.setOrderNumber(orderNumber);
        orderContainerAssign.setPickerName(pickerName);
        orderContainerAssign.setCustomerId(customerId);
        orderContainerAssign.setProductId(productId);
        // dynamic fields
        Long containerId = productContainerQuantityDTO.getContainerId();
        // validate container
        Container container = containerRepository.findById(containerId).orElseThrow();
        //        if(!containerIdSet.contains(containerId)) {
        //        }else containerIdSet.add(containerId);
        if (!container.isStatus()) {
          response
              .append("In this container ")
              .append(orderNumber)
              .append(" is already picked , please use another conatiner");
          return response.toString();
        } else {
          if (container.getOrderNumber().equals(orderNumber)) {

            orderContainerAssign.setContainerId(containerId);
            container.setStatus(false);
            containerRepository.save(container);
          }else{
            response
                    .append("In this container ")
                    .append(orderNumber)
                    .append(" is already picked , please use another conatiner");
            return response.toString();
          }
        }
        orderContainerAssign.setQuantity(productContainerQuantityDTO.getQuantity());
        orderContainerAssign.setAssignedOn(LocalDateTime.now());
        orderContainerAssign.setStatus(OrderStatus.PICKED);

        orderContainerAssignRespository.save(orderContainerAssign);
      }
    }
    // change in order info
    Optional<WarehouseOrdersInfo> savingWarehouseOrdersInfo =
        warehouseOrdersInfoRepository.findByOrderNumber(orderNumber);
    savingWarehouseOrdersInfo.ifPresent(
        (ordersInfo) -> {
          ordersInfo.setOrderStatus(OrderStatus.PICKED);
          warehouseOrdersInfoRepository.save(ordersInfo);
        });
    return response.toString();
  }

  public List<OrderContainerAssign> getAssignedProductsContainer(
      Long containerId, String orderNumber) {
    return orderContainerAssignRespository.findByContainerIdAndOrderNumber(
        containerId, orderNumber);
  }

  public Long getContainer() {

    return containerRepository.findFirstByStatus(true).getId();
  }
}
