package com.acs.acs.Enitities;

import com.acs.acs.ENUM.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wh_order_container_assigned")
public class OrderContainerAssign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long warehouseId;
    private String orderNumber;
    private Long containerId;
    private Long productId;
    private Long customerId;
    private Long quantity;
    private String pickerName;
    private LocalDateTime assignedOn;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Long boxId;
    private Integer boxLength;
    private Integer boxWidth;
    private Integer boxHeight;
    private Integer boxWeight;
}
