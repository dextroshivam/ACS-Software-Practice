package com.acs.acs.Enitities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "advance_shipment_notice_info")
public class AdvanceShipmentNoticeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poNumber;
    private String lotNumber;
    private Integer totalQuantity;
    private Long userId;


    private LocalDateTime createdOn;
}
