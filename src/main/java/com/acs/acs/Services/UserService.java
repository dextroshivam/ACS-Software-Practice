package com.acs.acs.Services;

import com.acs.acs.DTO.RequestDTO.UserRequest.UserLoginRequestDTO;
import com.acs.acs.DTO.RequestDTO.UserRequest.UserRequestDTO;
import com.acs.acs.DTO.ResponseDTO.UserResponse.*;
import com.acs.acs.Enitities.User;
import com.acs.acs.Enitities.Permission;
import com.acs.acs.Enitities.Role;
import com.acs.acs.Repository.PermissionRepository;
import com.acs.acs.Repository.RoleRepository;
import com.acs.acs.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
//        private String message;
//        private Boolean success;
//        UserResponseDTO userResponseDTO;
//        RoleResponseDTO roleResponseDTO;
//        PermissionResponseDTO permissionResponseDTO;

        // get message , sucess flag
        Optional<User> user = userRepository.findByEmail(userLoginRequestDTO.getEmail());
        if (!user.isPresent()) {
//            throw  new RuntimeException("Wrong Credentials");
            return new UserLoginResponseDTO("Wrong Credentials",false,null,null,null);
        }
        // Validate the password
        if (!user.get().getPassword().equals(userLoginRequestDTO.getPassword())) {
            return new UserLoginResponseDTO("Wrong Credentials",false,null,null,null);
//            throw new RuntimeException("Wrong Credentials");

        }

        // get PermissionResponseDTO,RoleResponseDTO,UserResponseDTO
        //        UserResponseDTO user;
        UserResponseDTO userResponseDTO = userToUserResponseDTO(user.get());


//        RoleResponseDTO role;
        Optional<Role> role=roleRepository.findById(user.get().getRoleId());
        RoleResponseDTO roleResponseDTO = roleToRoleResponseDTO(role);

//        PermissionResponseDTO permission;
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
//        System.out.println("done");

        List<Permission> permissions=permissionRepository.findByRoleId(role.get().getId());
        permissionResponseDTO.setPermissions(permissions);

        return new UserLoginResponseDTO("Login successful", true,userResponseDTO,roleResponseDTO,permissionResponseDTO);

    }

    public User addUser(UserRequestDTO userRequestDTO) {
        User user = UserRequestDTOToUser(userRequestDTO);
        user.setStatus(true);
        return userRepository.save(user);
    }
    public UniversalResponseDTO getUserById(long id) {

        UniversalResponseDTO universalRequestDTO = new UniversalResponseDTO();


//        UserResponseDTO user;
        User user=userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        UserResponseDTO userResponseDTO = userToUserResponseDTO(user);

//        RoleResponseDTO role;
        Optional<Role> role=roleRepository.findById(user.getRoleId());
        RoleResponseDTO roleResponseDTO = roleToRoleResponseDTO(role);

//        PermissionResponseDTO permission;
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
        System.out.println("done");

        List<Permission> permissions=permissionRepository.findByRoleId(role.get().getId());
        permissionResponseDTO.setPermissions(permissions);


        universalRequestDTO.setUserResponseDTO(userResponseDTO);
        universalRequestDTO.setRoleResponseDTO(roleResponseDTO);
        universalRequestDTO.setPermissionResponseDTO(permissionResponseDTO);

        return universalRequestDTO;
    }
//    private List<Permission> roleIdToPermissionsList(long roleId) {
//        List<Permission> permissions=
//        return permissions;
//    }
    private RoleResponseDTO roleToRoleResponseDTO(Optional<Role> role) {
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
//        private Long id;
//        private RoleType roletype;
//        private String roleDescription;
//        private boolean status; // Include status for response
        roleResponseDTO.setId(role.get().getId());
        roleResponseDTO.setRoleType(role.get().getRoleType());
        roleResponseDTO.setRoleDescription(role.get().getRoleDescription());
        roleResponseDTO.setStatus(role.get().getStatus());
        return roleResponseDTO;
    }
    private UserResponseDTO userToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setPhone(user.getPhone());
        userResponseDTO.setStatus(user.getStatus());
        Optional<Role> role=roleRepository.findById(user.getRoleId());
        userResponseDTO.setRole(role.get().getRoleType());
        return userResponseDTO;
    }
    private User UserRequestDTOToUser(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhone(userRequestDTO.getPhone());
        user.setRoleId(userRequestDTO.getRoleId());
        return user;
    }
//    public UserResponseDTO updateUserById(UserRequestDTO userRequestDTO, long id) {
////        User user=UserRequestDTOToUser(userRequestDTO);
//        User user=userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
//        user.setName(userRequestDTO.getName());
//        user.setPassword(userRequestDTO.getPassword());
//        user.setEmail(userRequestDTO.getEmail());
//        user.setPhone(userRequestDTO.getPhone());
//        user.setRoleId(userRequestDTO.getRoleId());
//        user.setStatus(true);
//        userRepository.save(user);
//        // create respnoseDTO
//        UserResponseDTO responseDTO = new UserResponseDTO();
//        responseDTO.setId(id);
//        responseDTO.setEmail(userRequestDTO.getEmail());
//        responseDTO.setName(userRequestDTO.getName());
//        responseDTO.setPhone(userRequestDTO.getPhone());
//        responseDTO.setStatus(user.isStatus());
//        // get role
//        Optional<Role> role=roleRepository.findById(user.getRoleId());
//        responseDTO.setRole(role.get().getRoleType());
//        // get permission
//        Optional<Permission> permission=permissionRepository.
//                findById(role.get().getPermissionId());
//        responseDTO.setPermission(permission.get());
//        return responseDTO;
//
//    }
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
