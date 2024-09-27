package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.ASNRequest.ASNRequestDTO;
import com.acs.acs.DTO.ResponseDTO.ASNResponse.ASNResponseDTO;
import com.acs.acs.Enitities.AdvanceShipmentNoticeInfo;
import com.acs.acs.Enitities.WarehouseReceivedItems;
import com.acs.acs.Services.ASNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asn")
public class ASNController {

    @Autowired
    private ASNService asnService;

    // API to create ASN, ASN unit, and warehouse received item
    @PostMapping("/add")
    public ResponseEntity<ASNResponseDTO> createASN(@RequestBody ASNRequestDTO asnRequestDTO) {
        ASNResponseDTO asnResponseDTO=asnService.createASN(asnRequestDTO);
        return ResponseEntity.ok(asnResponseDTO);
    }
//
//    // API to get all warehouse received items
//    @GetMapping("/get/warehouse-received-items")
//    public ResponseEntity<List<WarehouseReceivedItems>> findAllWarehouseReceivedItems() {
//        List<WarehouseReceivedItems> items = asnService.findAllWarehouseReceivedItems();
//        return ResponseEntity.ok(items);
//    }

//    // API to get warehouse received items by product ID
//    @GetMapping("/warehouse-received-items/{productId}")
//    public ResponseEntity<List<WarehouseReceivedItems>> getByProductId(@PathVariable Long productId) {
//        List<WarehouseReceivedItems> items = asnService.getWarehouseReceivedItemsByProductId(productId);
//        return ResponseEntity.ok(items);
//    }
}
