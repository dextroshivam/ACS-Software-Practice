package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.BoxDimensionsRequest.BoxDimensionsRequestDTO;
import com.acs.acs.DTO.RequestDTO.ShipmentRequest.ShipmentRequestDTO;
import com.acs.acs.DTO.ResponseDTO.ScanBoxLableResponse.ScanBoxLableResponse;
import com.acs.acs.DTO.ResponseDTO.ShipmentResponse.ShipmentResponseDTO;
import com.acs.acs.Services.ShippingService;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/ship")
public class ShippingController {

  @Autowired ShippingService shippingService;



  @GetMapping("/scanBoxLabel")
  public ScanBoxLableResponse scanBoxLabel(@RequestParam Long boxLabel) {
    return shippingService.scanBoxLabel(boxLabel);
  }
  @PostMapping("/setBoxDimensions")
  public ResponseEntity<String> setBoxDimensions(@RequestBody BoxDimensionsRequestDTO boxDimensionsRequestDTO) throws URISyntaxException {
    return shippingService.setBoxDimensions(boxDimensionsRequestDTO);
  }

//  @PostMapping("/generateShipment")
//  public ResponseEntity<ShipmentResponseDTO> generateShipment(@RequestParam String orderNumber
//      //      @RequestBody ShipmentRequestDTO shipmentRequestDTO
//      ) {
////    try {
////      ShipmentResponseDTO response =
////          shippingService.generateShipment(
////              orderNumber
////              //              shipmentRequestDTO
////              );
////      if (response != null) {
////        return ResponseEntity.badRequest().body(null);
////      } else return ResponseEntity.ok(response);
////    } catch (Exception e) {
////      return ResponseEntity.internalServerError().body(null);
////    }
//  }
}
