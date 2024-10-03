package com.acs.acs.DTO.RequestDTO.OrderRequest;

import lombok.Data;

import java.util.List;

@Data
public class OMSRequestDTO {
    String orderNumber;
    Long customerId;
    List<OMSProductDetailsDTO> productDetails;
}
