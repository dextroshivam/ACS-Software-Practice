package com.acs.acs.Services;

import com.acs.acs.Controllers.WarehouseOrderController;
import com.acs.acs.ENUM.OrderStatus;
import com.acs.acs.Enitities.OrderContainerAssign;
import com.acs.acs.Enitities.WarehouseBoxLabel;
import com.acs.acs.Enitities.WarehouseOrdersInfo;
import com.acs.acs.Repository.OrderContainerAssignRespository;
import com.acs.acs.Repository.WarehouseBoxRepository;
import com.acs.acs.Repository.WarehouseOrdersInfoRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PackService {

  @Autowired private OrderContainerAssignRespository orderContainerAssignRespository;
  @Autowired private WarehouseOrdersInfoRepository warehouseOrdersInfoRepository;
  @Autowired private WarehouseBoxRepository warehouseBoxRepository;

  public String packProduct(
      /*
       * 1. create another OrderContainerAssign and save with status and box id
       * 2. check box status and set into OrderContainerAssign;
       * */
      String orderNumber, Long containerId, Long productId, Long quantity, Long boxId) {
    String response = "";
    OrderContainerAssign presentOrderContainerAssign =
        orderContainerAssignRespository.findByOrderNumberAndProductIdAndQuantityAndContainerId(
            orderNumber, productId, quantity, containerId);
    if(presentOrderContainerAssign == null) {
      return "Order conatiner data is not available";
    }
    WarehouseBoxLabel warehouseBoxLabel = warehouseBoxRepository.findById(boxId).orElse(null);
    assert warehouseBoxLabel != null;
    boolean isBoxAvailable = warehouseBoxLabel.getBoxStatus();
    if (!isBoxAvailable) {
      return "Box is not available";
    }
    warehouseBoxLabel.setBoxStatus(false);
    Optional<WarehouseOrdersInfo> warehouseOrdersInfo =
        warehouseOrdersInfoRepository.findByOrderNumber(orderNumber);
    if (warehouseOrdersInfo.isPresent()) {

      warehouseOrdersInfo.get().setOrderStatus(OrderStatus.PACKED);
    }else{
      return "Order is not available";
    }
    OrderContainerAssign orderContainerAssign = new OrderContainerAssign();
    orderContainerAssign.setOrderNumber(presentOrderContainerAssign.getOrderNumber());
    orderContainerAssign.setContainerId(presentOrderContainerAssign.getContainerId());
    orderContainerAssign.setProductId(presentOrderContainerAssign.getProductId());
    orderContainerAssign.setCustomerId(presentOrderContainerAssign.getCustomerId());
    orderContainerAssign.setQuantity(quantity);
    orderContainerAssign.setPickerName(presentOrderContainerAssign.getPickerName());
    orderContainerAssign.setAssignedOn(LocalDateTime.now());
    orderContainerAssign.setStatus(OrderStatus.PACKED);
    orderContainerAssign.setBoxId(boxId);
    orderContainerAssignRespository.save(orderContainerAssign);
    return response;
  }
}
