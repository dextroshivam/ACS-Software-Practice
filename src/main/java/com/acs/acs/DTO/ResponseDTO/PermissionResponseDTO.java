package com.acs.acs.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseDTO {
    private Long id;
    private Long roleId; // Include if necessary for the response
    private boolean read;
    private boolean write;
    private boolean update;
    private boolean delete;
    private String status; // Include status for response
}
