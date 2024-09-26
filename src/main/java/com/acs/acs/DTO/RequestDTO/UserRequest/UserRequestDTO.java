package com.acs.acs.DTO.RequestDTO.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Long roleId;
}
