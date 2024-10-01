package com.acs.acs.Enitities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "oms_orders_info")
public class OMSOrdersInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false)
    private Long orderNumber;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "reason")
    private String reason;
}
