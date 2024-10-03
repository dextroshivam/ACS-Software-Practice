package com.acs.acs.Enitities;

import com.acs.acs.ENUM.InventoryStage;
import com.acs.acs.ENUM.OperationType;
import com.acs.acs.ENUM.ReceiveStatus;
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
@Table(name = "wh_received_item_logs")
public class WarehouseReceivedItemLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long warehouseId;
    private Long customerId;
    private String locationBarcode;

    @Enumerated(EnumType.STRING)
    private ReceiveStatus receiveStatus;

    @Enumerated(EnumType.STRING)
    private InventoryStage inventoryStage;

    private Long quantity;
    private String lotNumber;
    private Long userId;


    private LocalDateTime createdOn;
//    private Long operationSourceId;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
}


