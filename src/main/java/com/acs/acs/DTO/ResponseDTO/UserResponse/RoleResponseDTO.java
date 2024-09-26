package com.acs.acs.DTO.ResponseDTO.UserResponse;

import com.acs.acs.ENUM.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDTO {
    private Long id;
    private RoleType roleType;
    private String roleDescription;
    private boolean status; // Include status for response
//    private Set<PermissionResponseDTO> permissions;
}
