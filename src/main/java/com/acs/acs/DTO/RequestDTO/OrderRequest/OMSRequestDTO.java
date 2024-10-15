package com.acs.acs.DTO.RequestDTO.OrderRequest;

import com.acs.acs.ENUM.ServiceType;
import lombok.Data;

import java.util.List;

@Data
public class OMSRequestDTO {
    private Long warehouseId;
    private String orderNumber;
    private Long customerId;
    private Long shipToCityId;
    private Long shipToStateId;
    private Long shipToCountryId;
    private String shipToZipCode;
//    private Long carrierId;
    List<OMSProductDetailsDTO> productDetails;
}
