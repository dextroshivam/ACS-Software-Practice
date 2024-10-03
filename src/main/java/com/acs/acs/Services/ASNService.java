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
        // Create ASN info
        AdvanceShipmentNoticeInfo savedAdvanceShipmentNoticeInfo =
                saveAdvanceShipmentNoticeInfo(asnRequestDTO.getAsnInfoRequestDTO());
//        create ASN unit
        List<ASNUnitRequestDTO> units = asnRequestDTO.getUnits();
//        create response
        ASNResponseDTO asnResponseDTO = createASNResponseDTO(
                savedAdvanceShipmentNoticeInfo,asnRequestDTO,units);
        for (ASNUnitRequestDTO asnUnitRequestDTO : units) {
        // Create ASN units and warehouse item
            AdvanceShipmentNoticeUnit savedAdvanceShipmentNoticeUnit =
                    saveAdvanceShipmentNoticeUnit(savedAdvanceShipmentNoticeInfo,asnUnitRequestDTO);
            // Create Warehouse Received Items for each ASN unit
            WarehouseReceivedItems savedWarehouseReceivedItems = saveWarehouseReceivedItems(
                    asnUnitRequestDTO,asnRequestDTO, savedAdvanceShipmentNoticeUnit,
                    savedAdvanceShipmentNoticeInfo);
            // create log
            createLog(savedWarehouseReceivedItems,OperationType.Receive_Inventory);
        }
        return asnResponseDTO;
    }

    public void createLog(WarehouseReceivedItems warehouseReceivedItems, OperationType operationType){
        WarehouseReceivedItemLogs warehouseReceivedItemLogs = new WarehouseReceivedItemLogs();
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
    // saveAdvanceShipmentNoticeInfo
    private AdvanceShipmentNoticeInfo saveAdvanceShipmentNoticeInfo(ASNInfoRequestDTO asnInfoRequestDTO) {

        AdvanceShipmentNoticeInfo asnInfo = new AdvanceShipmentNoticeInfo();
        asnInfo.setPoNumber(asnInfoRequestDTO.getPoNumber());
        asnInfo.setLotNumber(asnInfoRequestDTO.getLotNumber());
        asnInfo.setTotalQuantity(asnInfoRequestDTO.getTotalQuantity());
        asnInfo.setUserId(asnInfoRequestDTO.getUserId());
        asnInfo.setCreatedOn(LocalDateTime.now());
        return asnInfoRepository.save(asnInfo);
    }

    // saveAdvanceShipmentNoticeUnit
    private AdvanceShipmentNoticeUnit saveAdvanceShipmentNoticeUnit(
            AdvanceShipmentNoticeInfo savedAdvanceShipmentNoticeInfo,
            ASNUnitRequestDTO asnUnitRequestDTO){
        AdvanceShipmentNoticeUnit asnUnit= new AdvanceShipmentNoticeUnit();
        asnUnit.setAsnId(savedAdvanceShipmentNoticeInfo.getId());
        asnUnit.setProductId(asnUnitRequestDTO.getProductId());
        asnUnit.setQuantity(asnUnitRequestDTO.getQuantity());
        asnUnit.setLocationBarcode("L1");
        asnUnit.setReceivedLocation("R1");
        return asnUnitRepository.save(asnUnit);

    }
    private WarehouseReceivedItems saveWarehouseReceivedItems(
            ASNUnitRequestDTO asnUnitRequestDTO,ASNRequestDTO asnRequestDTO,
            AdvanceShipmentNoticeUnit savedAdvanceShipmentNoticeUnit,
            AdvanceShipmentNoticeInfo savedAdvanceShipmentNoticeInfo
    ){
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
        return warehouseReceivedItemsRepository.save(receivedItem);
    }
    private ASNResponseDTO createASNResponseDTO(
            AdvanceShipmentNoticeInfo savedAdvanceShipmentNoticeInfo,
            ASNRequestDTO asnRequestDTO,List<ASNUnitRequestDTO> units){
        ASNResponseDTO asnResponseDTO = new ASNResponseDTO();
        asnResponseDTO.setAsnId(savedAdvanceShipmentNoticeInfo.getId());
        asnResponseDTO.setUserId(savedAdvanceShipmentNoticeInfo.getUserId());
        asnResponseDTO.setWarehouseId(asnRequestDTO.getAsnInfoRequestDTO().getWarehouseId());
        asnResponseDTO.setUnits(units);
        return asnResponseDTO;
    }

}

