package com.acs.acs.Controllers;


import com.acs.acs.DTO.DTOMapper.UserMapper;
import com.acs.acs.DTO.RequestDTO.UserRequestDTO;
import com.acs.acs.Enitities.User;
import com.acs.acs.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserRequestDTO userRequestDTO){
        User user = userMapper.userRequestDTOToUser(userRequestDTO);
        // complete user mapping
        user.setStatus(true);
        User savedUser=userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

}
