package com.acs.acs.DTO.RequestDTO.OrderRequest;

import lombok.Data;

import java.util.List;

@Data
public class OMSRequestDTO {
    Long orderNumber;
    Long clientId;
    List<OMSProductDetailsDTO> productDetails;
}
