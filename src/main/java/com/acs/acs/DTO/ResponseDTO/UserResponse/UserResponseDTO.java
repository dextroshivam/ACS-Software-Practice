package com.acs.acs.DTO.ResponseDTO.UserResponse;

import com.acs.acs.ENUM.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean status;// Include status for response
    private RoleType role;
//    private Permission permission;
}

