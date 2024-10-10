package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.OrderContainerRequest.OrderContainerAssignRequestDTO;
import com.acs.acs.Services.OrderContainerAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        }else{
            return response;
        }

    }
}
