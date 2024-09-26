package com.acs.acs.DTO.RequestDTO.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequestDTO {
    private boolean readPermission;
    private boolean writePermission;
    private boolean updatePermission;
    private boolean deletePermission;
}
