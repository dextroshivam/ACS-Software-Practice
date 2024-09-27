package com.acs.acs.Enitities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;
    private LocalDateTime createdOn;
    private Long createdBy;
    private Boolean status;
}
