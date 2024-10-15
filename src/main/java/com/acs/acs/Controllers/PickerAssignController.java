package com.acs.acs.Controllers;

import com.acs.acs.Services.PickerAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/picker")
public class PickerAssignController {

   @Autowired
   PickerAssignService pickerAssignService;
    @PostMapping("/assign")
    public ResponseEntity<String> assignPicker(@RequestParam String orderNumber, @RequestParam String pickerName,@RequestParam Long containerId) {
        String response=pickerAssignService.assignPicker(orderNumber,pickerName);
        return ResponseEntity.ok(response);
    }
}
