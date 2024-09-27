package com.acs.acs.DTO.ResponseDTO.ReportResponse;

import com.acs.acs.Enitities.Product;
import lombok.Data;

import java.util.List;

@Data
public class ReportResponseDTO {
    List<UserReportResponseDTO> userReportResponseDTOList;
    List<ProductReportResponseDTO> productReportResponseDTOList;
    List<CustomerReportResponseDTO> customerReportResponseDTOList;
}
