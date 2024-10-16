package com.acs.acs.Controllers;


import com.acs.acs.DTO.DTOMapper.UserMapper;
import com.acs.acs.DTO.RequestDTO.UserRequest.UserLoginRequestDTO;
import com.acs.acs.DTO.RequestDTO.UserRequest.UserRequestDTO;
import com.acs.acs.DTO.ResponseDTO.UserResponse.UniversalResponseDTO;
import com.acs.acs.DTO.ResponseDTO.UserResponse.UserLoginResponseDTO;
import com.acs.acs.DTO.ResponseDTO.UserResponse.UserResponseDTO;
import com.acs.acs.Enitities.User;
import com.acs.acs.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    /*



    *  access_modifier returnType name(parameter){
    *
    * }
    * */

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        try{
            User savedUser=userService.addUser(userRequestDTO);
            return ResponseEntity.ok(savedUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UniversalResponseDTO> getUserById(@PathVariable("id") long id){
        try{
            UniversalResponseDTO universalResponseDTO=userService.getUserById(id);
            return ResponseEntity.ok(universalResponseDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        UserLoginResponseDTO response = userService.login(userLoginRequestDTO);
        if (response.getSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }




//    @PutMapping("/update/{id}")
//    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") long id,@RequestBody UserRequestDTO userRequestDTO){
//        try{
//            System.out.println("R1");
//            UserResponseDTO userResponseDTO=userService.updateUserById(userRequestDTO,id);
//            return ResponseEntity.ok(userResponseDTO);
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
//    @GetMapping("/getAllUser")
//    public ResponseEntity<List<User>> getAllUser(){
//        try{
//            List<User> users=userService.getAllUser();
//            return ResponseEntity.ok(users);
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(null);
//        }
//    }



}
