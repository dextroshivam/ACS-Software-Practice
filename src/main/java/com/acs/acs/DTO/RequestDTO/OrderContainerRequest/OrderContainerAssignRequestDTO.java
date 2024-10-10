package com.acs.acs.DTO.RequestDTO.OrderContainerRequest;

import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderContainerAssignRequestDTO {
    private String orderNumber;
    private String pickerName;
    List<ProductAssignContainerDTO> productRequestsLists;
}
