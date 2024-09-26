package com.acs.acs.DTO.RequestDTO.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDTO {
    private String email;
    private String password;
}

