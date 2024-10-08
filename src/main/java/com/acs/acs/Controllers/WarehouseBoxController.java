package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.WarehouseBoxRequest.WarehouseBoxRequestDTO;
import com.acs.acs.Services.WarehouseBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/box")
public class WarehouseBoxController {

    @Autowired
    private WarehouseBoxService warehouseBoxService;

    @GetMapping("/getBoxes")
    public ResponseEntity<?> getBoxes(@RequestBody WarehouseBoxRequestDTO warehouseBoxDTO) {
        try{
            ResponseEntity<?> response=warehouseBoxService.getBoxes(warehouseBoxDTO);
            if(response.getStatusCode()== HttpStatus.NOT_FOUND){
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
