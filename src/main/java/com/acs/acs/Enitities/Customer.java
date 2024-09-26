package com.acs.acs.Enitities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String password;
    @NotNull
    private Boolean status;

    private String addressLine1;
    private String addressLine2;
    private Long cityId;
    private Long stateId;
    private Long countryId;
    private String zipCode;


}
