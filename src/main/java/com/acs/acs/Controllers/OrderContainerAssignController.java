package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.OrderContainerRequest.OrderContainerAssignRequestDTO;
import com.acs.acs.Enitities.OrderContainerAssign;
import com.acs.acs.Services.OrderContainerAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/container")
public class OrderContainerAssignController {

    @Autowired
    OrderContainerAssignService orderContainerAssignService;
    @PostMapping("/assign")
    public String assignContainer(@RequestBody OrderContainerAssignRequestDTO orderContainerAssignRequestDTO ) {

        String response=orderContainerAssignService.assignContainer(orderContainerAssignRequestDTO);
        if(response.isEmpty()){
            return "Container assignment Success";
        }else return response;
    }
    @GetMapping("/scanContainer")
    public ResponseEntity<List<OrderContainerAssign>> getAssignedProductsContainer(@RequestParam Long containerId,@RequestParam String orderNumber){
        try{
            List<OrderContainerAssign> response=orderContainerAssignService.getAssignedProductsContainer(containerId,orderNumber);
            if(response.isEmpty()){
                return ResponseEntity.notFound().eTag("There are no prodcuts.").build();
            }else return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/getContainer")
    public ResponseEntity<Long> getContainer(){
        Long response=orderContainerAssignService.getContainer();
        if(response==null){
            return ResponseEntity.notFound().eTag("There is no container").build();
        }else{
            return ResponseEntity.ok(response);
        }
    }
}
