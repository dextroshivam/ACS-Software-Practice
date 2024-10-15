package com.acs.acs.DTO.ResponseDTO.ScanBoxLableResponse;


import com.acs.acs.ENUM.ServiceType;
import com.acs.acs.Enitities.Address;
import com.acs.acs.Enitities.Partners;
import lombok.Data;

@Data
public class ScanBoxLableResponse {
    String orderNumber;
    Address shipFromAddress;
    Address shipToAddress;
    String carrierName;
    ServiceType serviceType;
}
