package com.acs.acs.Services;

import com.acs.acs.ENUM.OrderStatus;
import com.acs.acs.Enitities.PickerAssign;
import com.acs.acs.Enitities.WarehouseOrdersInfo;
import com.acs.acs.Repository.PickerAssignRepository;
import com.acs.acs.Repository.WarehouseOrdersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PickerAssignService {
  @Autowired PickerAssignRepository pickerAssignRepository;
  @Autowired WarehouseOrdersInfoRepository warehouseOrdersInfoRepository;

  public String assignPicker(String orderNumber, String pickerName) {
    PickerAssign pickerAssign = new PickerAssign();
    pickerAssign.setOrderNumber(orderNumber);
    pickerAssign.setPickerName(pickerName);
    pickerAssign.setStatus(true);
    pickerAssign.setCreatedOn(LocalDateTime.now());
    pickerAssign.setUpdatedOn(LocalDateTime.now());
    pickerAssignRepository.save(pickerAssign);
    // update warehouseinfo
    Optional<WarehouseOrdersInfo> warehouseOrdersInfo =
        warehouseOrdersInfoRepository.findByOrderNumber(orderNumber);
      //      warehouseOrdersInfoRepository.save(warehouseOrdersInfo.get());
      warehouseOrdersInfo.ifPresent(ordersInfo -> ordersInfo.setOrderStatus(OrderStatus.ASSIGNED));
    return "Picker Assigned";
  }
}
