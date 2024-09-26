package com.acs.acs.Enitities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String shortName;

//    @ManyToOne
//    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private Long stateId;

//    @ManyToOne
//    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Long countryId;
}
