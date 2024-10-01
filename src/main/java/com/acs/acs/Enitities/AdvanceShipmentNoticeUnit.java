package com.acs.acs.Enitities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "advance_shipment_notice_unit")
public class AdvanceShipmentNoticeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asnId;
    private Long productId;
    private Integer quantity;
    private String locationBarcode;
    private String receivedLocation;
}
