package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.BoxDimensionsRequest.BoxDimensionsRequestDTO;
import com.acs.acs.Services.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pack")
@RestController
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping("/packProduct")
    public String packProduct(@RequestParam String orderNumber, @RequestParam Long containerId,
                              @RequestParam Long productId, @RequestParam
                              Long quantity,@RequestParam Long boxId){
        try{
            String response=packService.packProduct(orderNumber,containerId,productId,quantity,boxId);
            if(response.isEmpty()){
                return "Packed";
            }else{
                return response;
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/setBoxDimensions")
    public String setBoxDimensions(@RequestBody BoxDimensionsRequestDTO boxDimensionsRequestDTO){
        return packService.setBoxDimensions(boxDimensionsRequestDTO);
    }

}
