package com.acs.acs.Services;
import com.acs.acs.DTO.RequestDTO.ASNRequest.ASNInfoRequestDTO;
import com.acs.acs.DTO.RequestDTO.ASNRequest.ASNRequestDTO;
import com.acs.acs.DTO.RequestDTO.ASNRequest.ASNUnitRequestDTO;
import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductAttributeRequestDTO;
import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductRequestDTO;
import com.acs.acs.DTO.ResponseDTO.ASNResponse.ASNResponseDTO;
import com.acs.acs.ENUM.InventoryStage;
import com.acs.acs.ENUM.OperationType;
import com.acs.acs.ENUM.ReceiveStatus;
import com.acs.acs.Enitities.*;
import com.acs.acs.Repository.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ASNService {

    @Autowired
    private AdvanceShipmentNoticeInfoRepository asnInfoRepository;

    @Autowired
    private AdvanceShipmentNoticeUnitRepository asnUnitRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseReceivedItemsRepository warehouseReceivedItemsRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseReceivedItemLogsRepository warehouseReceivedItemLogsRepository;

    // Create ASN, ASN unit, and warehouse received item
    public ASNResponseDTO createASN(ASNRequestDTO asnRequestDTO) {
//        Goals:
//        1. ASNResponseDTO done
//        2. Create entry in asninof -- done
//        3. create entry in asnunit
//        4. create entry in warehouseitems





        ASNInfoRequestDTO asnInfoRequestDTO = asnRequestDTO.getAsnInfoRequestDTO();

        // Create ASN info
        AdvanceShipmentNoticeInfo asnInfo = new AdvanceShipmentNoticeInfo();
        asnInfo.setPoNumber(asnInfoRequestDTO.getPoNumber());
        asnInfo.setLotNumber(asnInfoRequestDTO.getLotNumber());
        asnInfo.setTotalQuantity(asnInfoRequestDTO.getTotalQuantity());
        asnInfo.setUserId(asnInfoRequestDTO.getUserId());
        asnInfo.setCreatedOn(LocalDateTime.now());
        // Goal 2
        AdvanceShipmentNoticeInfo savedAdvanceShipmentNoticeInfo = asnInfoRepository.save(asnInfo);
//        createLog(savedAdvanceShipmentNoticeInfo.getId(),OperationType.Create_ASN);

        // Goal 1.4
        List<ASNUnitRequestDTO> units=asnRequestDTO.getUnits();
//        Goal 1 :
        ASNResponseDTO asnResponseDTO = new ASNResponseDTO();
        asnResponseDTO.setAsnId(savedAdvanceShipmentNoticeInfo.getId());
        asnResponseDTO.setUserId(savedAdvanceShipmentNoticeInfo.getUserId());
        asnResponseDTO.setWarehouseId(asnRequestDTO.getAsnInfoRequestDTO().getWarehouseId());
        asnResponseDTO.setUnits(units);
        // Create ASN units and warehouse item
        for (ASNUnitRequestDTO asnUnitRequestDTO : units) {

            AdvanceShipmentNoticeUnit asnUnit = new AdvanceShipmentNoticeUnit();
            asnUnit.setAsnId(savedAdvanceShipmentNoticeInfo.getId());
            // create product for product id and quantity

            asnUnit.setProductId(asnUnitRequestDTO.getProductId());
            asnUnit.setQuantity(asnUnitRequestDTO.getQuantity());
            asnUnit.setLocationBarcode("L1");
            asnUnit.setReceivedLocation("R1");
            AdvanceShipmentNoticeUnit savedAdvanceShipmentNoticeUnit= asnUnitRepository.save(asnUnit);
//            createLog(savedAdvanceShipmentNoticeUnit.getId(),OperationType.Create_ASN_Unit);

            // Create Warehouse Received Items for each ASN unit
            WarehouseReceivedItems receivedItem = new WarehouseReceivedItems();

            receivedItem.setProductId(asnUnitRequestDTO.getProductId());
            receivedItem.setWarehouseId(asnRequestDTO.getAsnInfoRequestDTO().getWarehouseId());
            receivedItem.setCustomerId(asnRequestDTO.getAsnInfoRequestDTO().getCustomerId());
            receivedItem.setLocationBarcode(savedAdvanceShipmentNoticeUnit.getLocationBarcode());
            receivedItem.setReceiveStatus(ReceiveStatus.RECEIVED); // Default status
            receivedItem.setInventoryStage(InventoryStage.ON_HAND); // Default stage
            receivedItem.setQuantity(asnUnitRequestDTO.getQuantity());
            receivedItem.setLotNumber(asnRequestDTO.getAsnInfoRequestDTO().getLotNumber());
            receivedItem.setUserId(asnRequestDTO.getAsnInfoRequestDTO().getUserId());
            receivedItem.setCreatedOn(savedAdvanceShipmentNoticeInfo.getCreatedOn());
            WarehouseReceivedItems savedWarehouseReceivedItems= warehouseReceivedItemsRepository.save(receivedItem);
            createLog(savedWarehouseReceivedItems,OperationType.Receive_Inventory);
        }


        return asnResponseDTO;
    }

    public void createLog(WarehouseReceivedItems warehouseReceivedItems, OperationType operationType){
        WarehouseReceivedItemLogs warehouseReceivedItemLogs = new WarehouseReceivedItemLogs();
//        private Long productId;
//        private Long warehouseId;
//        private Long customerId;
//        private String locationBarcode;
//        @Enumerated(EnumType.STRING)
//        private ReceiveStatus receiveStatus;
//        @Enumerated(EnumType.STRING)
//        private InventoryStage inventoryStage;
//        private Integer quantity;
//        private String lotNumber;
//        private Long userId;
//        @Temporal(TemporalType.TIMESTAMP)
//        private Date createdOn;
////    private Long operationSourceId;
//        @Enumerated(EnumType.STRING)
//        private OperationType operationType;
        warehouseReceivedItemLogs.setProductId(warehouseReceivedItems.getProductId());
        warehouseReceivedItemLogs.setWarehouseId(warehouseReceivedItems.getWarehouseId());
        warehouseReceivedItemLogs.setCustomerId(warehouseReceivedItems.getCustomerId());
        warehouseReceivedItemLogs.setLocationBarcode(warehouseReceivedItems.getLocationBarcode());
        warehouseReceivedItemLogs.setReceiveStatus(warehouseReceivedItems.getReceiveStatus());
        warehouseReceivedItemLogs.setInventoryStage(warehouseReceivedItems.getInventoryStage());
        warehouseReceivedItemLogs.setQuantity(warehouseReceivedItems.getQuantity());
        warehouseReceivedItemLogs.setLotNumber(warehouseReceivedItems.getLotNumber());
        warehouseReceivedItemLogs.setUserId(warehouseReceivedItems.getUserId());
        warehouseReceivedItemLogs.setCreatedOn(warehouseReceivedItems.getCreatedOn());
        warehouseReceivedItemLogs.setOperationType(operationType);
        warehouseReceivedItemLogsRepository.save(warehouseReceivedItemLogs);
//        return warehouseReceivedItemLogs;
    }

}

