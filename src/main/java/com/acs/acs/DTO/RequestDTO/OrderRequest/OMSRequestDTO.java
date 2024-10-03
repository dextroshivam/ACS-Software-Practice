package com.acs.acs.DTO.RequestDTO.OrderRequest;

import com.acs.acs.ENUM.ServiceType;
import lombok.Data;

import java.util.List;

@Data
public class OMSRequestDTO {
    private String orderNumber;
    private Long customerId;
    private Long shipToCityId;
    private Long shipToStateId;
    private Long shipToCountryId;
    private String shipToZipCode;
    private Long carrierId;
    private String carrierName;
    private ServiceType serviceType;
    List<OMSProductDetailsDTO> productDetails;
}
