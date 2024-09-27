package com.acs.acs.Services;

import com.acs.acs.Enitities.WarehouseReceivedItems;
import com.acs.acs.Repository.WarehouseReceivedItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    WarehouseReceivedItemsRepository warehouseReceivedItemsRepository;



    // Get warehouse received items by product ID
    public List<WarehouseReceivedItems> getWarehouseReceivedItemsByProductId(Long productId) {
        return warehouseReceivedItemsRepository.findByProductId(productId);
    }

    // Find all warehouse received items
    public List<WarehouseReceivedItems> findAllWarehouseReceivedItems() {
        return warehouseReceivedItemsRepository.findAll();
    }
}
