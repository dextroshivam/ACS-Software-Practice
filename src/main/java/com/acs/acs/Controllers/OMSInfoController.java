package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.OrderRequest.OMSRequestDTO;
import com.acs.acs.Services.OMSInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oms")
public class OMSInfoController {

    @Autowired
    OMSInfoService omsInfoService;

    @PostMapping("/add/orderDetails")
    public ResponseEntity<String> addOrderDetailsInOMS(@RequestBody OMSRequestDTO omsRequestDTO) {
        try{
            String response = omsInfoService.addOrderDetailsInOMS(omsRequestDTO);
            if(response.isEmpty())
                return ResponseEntity.ok(response);
            else return ResponseEntity.badRequest().body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
