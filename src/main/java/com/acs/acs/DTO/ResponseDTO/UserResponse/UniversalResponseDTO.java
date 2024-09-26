package com.acs.acs.DTO.ResponseDTO.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversalResponseDTO {
    UserResponseDTO userResponseDTO;
    RoleResponseDTO roleResponseDTO;
    PermissionResponseDTO permissionResponseDTO;
}
