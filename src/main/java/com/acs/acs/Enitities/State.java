package com.acs.acs.Enitities;

import com.acs.acs.ENUM.StateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String shortName;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

//    @ManyToOne
//    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Long countryId;
}
