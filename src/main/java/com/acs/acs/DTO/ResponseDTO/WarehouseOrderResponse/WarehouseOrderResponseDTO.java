package com.acs.acs.DTO.ResponseDTO.WarehouseOrderResponse;

import com.acs.acs.Enitities.WarehouseOrdersItems;
import lombok.Data;

import java.util.List;

@Data
public class WarehouseOrderResponseDTO {
    private String orderNumber;
    List<Long> productIdList;
}
