package com.acs.acs.DTO.RequestDTO.ASNRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASNInfoRequestDTO {
    private String poNumber;
    private String lotNumber;
    private Integer totalQuantity;
    private Long userId;
    private Long warehouseId;
    private Long customerId;
}
