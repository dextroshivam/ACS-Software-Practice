package com.acs.acs.Controllers;

import com.acs.acs.DTO.ResponseDTO.ScanBoxLableResponse.ScanBoxLableResponse;
import com.acs.acs.Services.ShippingService;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ship")
public class ShippingController {

  @Autowired ShippingService shippingService;

  @GetMapping("/scanBoxLabel")
  public ScanBoxLableResponse scanBoxLabel(@RequestParam Long boxLabel) {
    return shippingService.scanBoxLabel(boxLabel);
  }
}
