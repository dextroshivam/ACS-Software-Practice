package com.acs.acs.DTO.RequestDTO.OrderContainerRequest;

import lombok.Data;

import java.util.List;

@Data
public class ProductAssignContainerDTO {

    private Long productId;
    List<ProductContainerQuantityDTO> productContainerQuantityDTOList;

}
