package com.acs.acs.DTO.RequestDTO.WarehouseBoxRequest;

import com.acs.acs.ENUM.BoxType;
import lombok.Data;

@Data
public class WarehouseBoxRequestDTO {

    private Long warehouseId;
    private BoxType boxType;
    private Integer quantity;
}
