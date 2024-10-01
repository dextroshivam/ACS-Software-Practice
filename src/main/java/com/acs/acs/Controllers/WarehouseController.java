package com.acs.acs.Controllers;

import com.acs.acs.Enitities.WarehouseReceivedItems;
import com.acs.acs.Services.ASNService;
import com.acs.acs.Services.WarehouseService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/warehosue")
public class WarehouseController {


    @Autowired
    ASNService asnService;
    @Autowired
    WarehouseService warehouseService;
    // create implimentation is in the asn service
    // API to get warehouse received items by product ID
    @GetMapping("/items/{productId}")
    public ResponseEntity<List<WarehouseReceivedItems>> getByProductId(@PathVariable Long productId) {
        List<WarehouseReceivedItems> items = warehouseService.getWarehouseReceivedItemsByProductId(productId);
        return ResponseEntity.ok(items);
    }

    // API to get all warehouse received items
    @GetMapping("/get/warehouse-received-items")
    public ResponseEntity<List<WarehouseReceivedItems>> findAllWarehouseReceivedItems() {
        List<WarehouseReceivedItems> items = warehouseService.findAllWarehouseReceivedItems();
        return ResponseEntity.ok(items);
    }


}
