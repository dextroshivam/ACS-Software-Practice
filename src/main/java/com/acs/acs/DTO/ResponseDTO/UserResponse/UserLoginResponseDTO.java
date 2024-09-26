package com.acs.acs.DTO.ResponseDTO.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDTO {
    private String message;
    private Boolean success;
    UserResponseDTO userResponseDTO;
    RoleResponseDTO roleResponseDTO;
    PermissionResponseDTO permissionResponseDTO;
}
