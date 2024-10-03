package com.acs.acs.Enitities;

import com.acs.acs.ENUM.OrderStatus;
import com.acs.acs.ENUM.ServiceType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wh_orders_info")
public class WarehouseOrdersInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "client_id", nullable = false)
    private Long customerId;
    private OrderStatus orderStatus;

    private Long shipToCityId;
    private Long shipToStateId;
    private Long shipToCountryId;
    private String shipToZipCode;
    private Long carrierId;
    private String carrierName;
    private ServiceType serviceType;

}

