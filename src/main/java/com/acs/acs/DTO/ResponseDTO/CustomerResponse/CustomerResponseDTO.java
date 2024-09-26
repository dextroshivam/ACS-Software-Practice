package com.acs.acs.DTO.ResponseDTO.CustomerResponse;

//import com.acs.acs.Enitities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
