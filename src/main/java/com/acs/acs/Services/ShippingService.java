package com.acs.acs.Services;

import com.acs.acs.DTO.ResponseDTO.ScanBoxLableResponse.ScanBoxLableResponse;
import com.acs.acs.ENUM.OrderStatus;
import com.acs.acs.ENUM.ServiceType;
import com.acs.acs.Enitities.*;
import com.acs.acs.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

  @Autowired OrderContainerAssignRespository orderContainerAssignRespository;

  @Autowired WarehouseRepository warehouseRepository;
  @Autowired WarehouseBoxRepository warehouseBoxRepository;
  @Autowired WarehouseOrdersInfoRepository warehouseOrdersInfoRepository;
  @Autowired PartnersRepository partnersRepository;

  public ScanBoxLableResponse scanBoxLabel(Long boxLabel) {
    ScanBoxLableResponse scanBoxLableResponse = new ScanBoxLableResponse();
    //        String orderNumber;
    //        Address shipFromAddress;
    //        Address shipToAddress;
    //        String carrierName;
    //        ServiceType serviceType;
    //        --> change status to ready to ship
    OrderContainerAssign packedOrder =
        orderContainerAssignRespository.findByBoxIdAndStatus(boxLabel, OrderStatus.PACKED);

    //    scanBoxLableResponse.setOrderNumber(packedOrder.getOrderNumber());
    Address shipFromAddress = new Address();
    //      String addressLine1;
    //      String addressLine2;
    //      Long stateId;
    //      Long cityId;
    //      Long countyId;
    //      String zipCode;
    Long warehouseId = warehouseBoxRepository.findById(boxLabel).get().getWarehouseId();
    Warehouse warehouse = warehouseRepository.findById(warehouseId).get();
    shipFromAddress.setAddressLine1(warehouse.getAddressLine1());
    shipFromAddress.setAddressLine2(warehouse.getAddressLine2());
    shipFromAddress.setStateId(warehouse.getStateId());
    shipFromAddress.setZipCode(warehouse.getZipCode());
    shipFromAddress.setCityId(warehouse.getCityId());
    shipFromAddress.setCountyId(warehouse.getCountyId());

    scanBoxLableResponse.setShipFromAddress(shipFromAddress);
    Address shipToAddress = new Address();

    String orderNumber = packedOrder.getOrderNumber();
    WarehouseOrdersInfo warehouseOrdersInfo =
        warehouseOrdersInfoRepository.findByOrderNumber(orderNumber).get();
    scanBoxLableResponse.setOrderNumber(orderNumber);
    shipToAddress.setAddressLine1(warehouseOrdersInfo.getAddressLine1());
    shipToAddress.setAddressLine2(warehouseOrdersInfo.getAddressLine2());
    shipToAddress.setCityId(warehouseOrdersInfo.getShipToCityId());
    shipToAddress.setStateId(warehouseOrdersInfo.getShipToStateId());
    shipToAddress.setCountyId(warehouseOrdersInfo.getShipToCountryId());
    shipToAddress.setZipCode(warehouseOrdersInfo.getShipToZipCode());

    scanBoxLableResponse.setShipToAddress(shipToAddress);
    Partners partners=partnersRepository.findByName(warehouseOrdersInfo.getCarrierName());
    scanBoxLableResponse.setCarrierName(partners.getName());
    scanBoxLableResponse.setServiceType(partners.getServiceType());
    return scanBoxLableResponse;
  }
}
