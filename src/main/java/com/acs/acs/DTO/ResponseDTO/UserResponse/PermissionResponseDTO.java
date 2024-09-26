package com.acs.acs.DTO.ResponseDTO.UserResponse;

import com.acs.acs.Enitities.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseDTO {
//    private Long id;
//    private Long roleId; // Include if necessary for the response
//    private boolean read;
//    private boolean write;
//    private boolean update;
//    private boolean delete;
//    private boolean status; // Include status for response
    private List<Permission> permissions;
}
