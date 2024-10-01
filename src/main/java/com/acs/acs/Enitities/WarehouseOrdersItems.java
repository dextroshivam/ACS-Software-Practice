package com.acs.acs.Enitities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wh_orders_items")
public class WarehouseOrdersItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_quantity", nullable = false)
    private Long productQuantity;
    private Long warehouseOrderInfoId;

//    @ManyToOne
//    @JoinColumn(name = "warehouse_orders_info_id", referencedColumnName = "id")
//    private WarehouseOrdersInfo warehouseOrdersInfo;
}
