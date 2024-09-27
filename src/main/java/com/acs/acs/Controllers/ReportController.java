package com.acs.acs.Controllers;


import com.acs.acs.DTO.ResponseDTO.ReportResponse.CustomerReportResponseDTO;
import com.acs.acs.DTO.ResponseDTO.ReportResponse.ProductReportResponseDTO;
import com.acs.acs.DTO.ResponseDTO.ReportResponse.ReportResponseDTO;
import com.acs.acs.DTO.ResponseDTO.ReportResponse.UserReportResponseDTO;
import com.acs.acs.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getReport(@PathVariable Long id) {
        try{
            if (id == 1) {
//              List<UserReportResponseDTO> userReportResponseDTO=reportService.getReport(id);
                Object userReportResponseDTO = reportService.getReport(id);
                return ResponseEntity.ok(userReportResponseDTO);
            } else if (id == 2) {
//              List<ProductReportResponseDTO> productReportResponseDTO=reportService.getReport(id);
                Object productReportResponseDTO = reportService.getReport(id);
                return ResponseEntity.ok(productReportResponseDTO);
            } else if (id == 3) {
//              List<CustomerReportResponseDTO> customerReportResponseDTO=reportService.getReport(id);
                Object customerReportResponseDTO = reportService.getReport(id);
                return ResponseEntity.ok(customerReportResponseDTO);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return  ResponseEntity.badRequest().body("There was an error");
    }

}
