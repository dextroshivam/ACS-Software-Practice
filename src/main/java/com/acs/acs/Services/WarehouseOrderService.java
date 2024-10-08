package com.acs.acs.Services;

import com.acs.acs.DTO.ResponseDTO.WarehouseOrderResponse.WarehouseOrderResponseDTO;
import com.acs.acs.Enitities.WarehouseOrdersInfo;
import com.acs.acs.Enitities.WarehouseOrdersItems;
import com.acs.acs.Repository.WarehouseOrdersInfoRepository;
import com.acs.acs.Repository.WarehouseOrdersItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseOrderService {

  @Autowired WarehouseOrdersItemsRepository warehouseOrdersItemsRepository;
  @Autowired WarehouseOrdersInfoRepository warehouseOrdersInfoRepository;

  public WarehouseOrderResponseDTO getByOrderNumber(String orderNumber) {
    // response dto
    WarehouseOrderResponseDTO warehouseOrderResponseDTO = new WarehouseOrderResponseDTO();
    warehouseOrderResponseDTO.setOrderNumber(orderNumber);

    // check if warehouse info is present
    Optional<WarehouseOrdersInfo> warehouseOrderInfo =
        warehouseOrdersInfoRepository.findByOrderNumber(orderNumber);

    // return null if not present
    if (warehouseOrderInfo.isEmpty()) {
      warehouseOrderResponseDTO.setProductIdList(null);
      return warehouseOrderResponseDTO;
    }

    Long warehouseOrderInfoId = warehouseOrderInfo.get().getId();
    List<WarehouseOrdersItems> warehouseOrdersItems =
        warehouseOrdersItemsRepository.findByWarehouseOrderInfoId(warehouseOrderInfoId);

    List<Long> productIdList=warehouseOrdersItems.stream().map(WarehouseOrdersItems::getProductId).
            toList();
    for(WarehouseOrdersItems warehouseOrdersItem : warehouseOrdersItems) {
      productIdList.add(warehouseOrdersItem.getProductId());
    }
    warehouseOrderResponseDTO.setProductIdList(productIdList);
    return warehouseOrderResponseDTO;
  }
}
