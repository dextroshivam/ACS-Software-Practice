package com.acs.acs.Enitities;

import com.acs.acs.ENUM.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long carrierId;
    private String name;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    private Boolean is_default;
//    private Long customerId;
}
