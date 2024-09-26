package com.acs.acs.DTO.RequestDTO.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {
    private String roleName;
    private String roleDescription;
}
