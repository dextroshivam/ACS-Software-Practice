package com.acs.acs.Services;


import com.acs.acs.DTO.RequestDTO.OrderRequest.OMSProductDetailsDTO;
import com.acs.acs.DTO.RequestDTO.OrderRequest.OMSRequestDTO;
import com.acs.acs.Enitities.*;
import com.acs.acs.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OMSInfoService {

    @Autowired
    OMSInfoRepository omsInfoRepository;
    @Autowired
    OMSOrdersItemsRepository omsOrdersItemsRepository;
    @Autowired
    WarehouseReceivedItemsRepository warehouseReceivedItemsRepository;
    @Autowired
    WarehouseOrdersInfoRepository warehouseOrdersInfoRepository;
    @Autowired
    WarehouseOrdersItemsRepository warehouseOrdersItemsRepository;

    public String addOrderDetailsInOMS(OMSRequestDTO omsRequestDTO) {
        // save oms info
         StringBuilder ans= new StringBuilder();
        OMSOrdersInfo omsOrdersInfo = new OMSOrdersInfo();
        omsOrdersInfo.setClientId(omsRequestDTO.getClientId());
        OMSOrdersInfo savedOMSOrdersInfo = omsInfoRepository.save(omsOrdersInfo);
        List<OMSProductDetailsDTO> omsProductDetailsDTOList = omsRequestDTO.getProductDetails();
        boolean isErrorFlag=false;
        for(OMSProductDetailsDTO omsProductDetailsDTO:omsProductDetailsDTOList){
            Optional<WarehouseReceivedItems> warehouseReceivedItems =
                    warehouseReceivedItemsRepository.
                            findOneByProductId(omsProductDetailsDTO.getProductId());
            if(warehouseReceivedItems.isPresent()){
                if(warehouseReceivedItems.get().getQuantity()==0){
                    isErrorFlag=true;
                    ans.append("product with product id: ").append(omsProductDetailsDTO.getProductId()).append(" has no quantity\n");
                } else if (warehouseReceivedItems.get().getQuantity() < omsProductDetailsDTO.getQuantity()) {
                    isErrorFlag=true;
                    ans.append("product with product id: ").append(omsProductDetailsDTO.getProductId()).append(" is not available in sufficient amount\n");
                }
                OMSOrdersItems omsOrdersItems = new OMSOrdersItems();
                omsOrdersItems.setProductId(omsProductDetailsDTO.getProductId());
                omsOrdersItems.setProductQuantity(omsProductDetailsDTO.getQuantity());
                omsOrdersItems.setOmsOrdersInfoId(savedOMSOrdersInfo.getId());
                omsOrdersItemsRepository.save(omsOrdersItems);
            }else {
                isErrorFlag = true;
                ans.append("Product with product id: ").append(omsProductDetailsDTO.getProductId()).append(" is not a valid product\n");
            }
        }
        if(isErrorFlag){
            savedOMSOrdersInfo.setReason("There is an error");
            omsInfoRepository.save(savedOMSOrdersInfo);
            return ans.toString();
        }
        WarehouseOrdersInfo warehouseOrdersInfo = new WarehouseOrdersInfo();
        warehouseOrdersInfo.setOrderNumber(omsRequestDTO.getOrderNumber());
        warehouseOrdersInfo.setClientId(omsRequestDTO.getClientId());
        System.out.println("Pointer 7");
        WarehouseOrdersInfo savedWarehouseOrdersInfo = warehouseOrdersInfoRepository.save(warehouseOrdersInfo);
        for(OMSProductDetailsDTO omsProductDetailsDTO:omsProductDetailsDTOList){
            WarehouseOrdersItems warehouseOrdersItems = new WarehouseOrdersItems();
            warehouseOrdersItems.setProductId(omsProductDetailsDTO.getProductId());
            warehouseOrdersItems.setProductQuantity(omsProductDetailsDTO.getQuantity());
            warehouseOrdersItems.setWarehouseOrderInfoId(savedWarehouseOrdersInfo.getId());
            warehouseOrdersItemsRepository.save(warehouseOrdersItems);
        }
        return ans.toString();
    }
    private void saveOrderInWarehouse(){

    }
}
