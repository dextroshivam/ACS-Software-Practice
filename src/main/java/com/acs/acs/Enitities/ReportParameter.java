package com.acs.acs.Enitities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reportId;

    private String parameter;
}
