package com.acs.acs.DTO.RequestDTO.CustomerRequest;

//import com.acs.acs.Enitities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String addressLine1;
    private String addressLine2;
    private Long cityId;
    private Long stateId;
    private Long countryId;
    private String zipCode;
}
