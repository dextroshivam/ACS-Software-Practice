package com.acs.acs.Controllers;

import com.acs.acs.DTO.ResponseDTO.WarehouseOrderResponse.WarehouseOrderResponseDTO;
import com.acs.acs.Enitities.WarehouseReceivedItems;
import com.acs.acs.Services.WarehouseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/order")
public class WarehouseOrderController {

    @Autowired
    WarehouseOrderService warehouseOrderService;

    // Get product by order number
    @GetMapping("/items/orderNumber/{orderNumber}")
    public ResponseEntity<WarehouseOrderResponseDTO> getByOrderNumber(@PathVariable String orderNumber) {
        WarehouseOrderResponseDTO response =warehouseOrderService.getByOrderNumber(orderNumber);
        if(response.getProductIdList()==null){
            return ResponseEntity.notFound().eTag("There are no items with this order number").build();
        }else {
            return ResponseEntity.ok(response);
        }
    }
}