package com.acs.acs.Enitities;

import com.acs.acs.ENUM.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//import javax.management.relation.Role;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private Boolean status;
    private String password;
    private Long roleId;
//    private List<Permission> permissions;
}
