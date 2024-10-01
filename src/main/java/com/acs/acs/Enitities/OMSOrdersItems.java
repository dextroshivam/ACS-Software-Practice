package com.acs.acs.Enitities;

import com.acs.acs.ENUM.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "oms_orders_items")
public class OMSOrdersItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_quantity", nullable = false)
    private Long productQuantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "oms_orders_info_id",nullable = false)
    Long omsOrdersInfoId;

//    @ManyToOne
//    @JoinColumn(name = "oms_order_info_id", referencedColumnName = "id")
//    private OMSOrdersInfo omsOrderInfo;
}
