package com.acs.acs.Repository;

import com.acs.acs.Enitities.Product;
import com.acs.acs.Enitities.WarehouseReceivedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseReceivedItemsRepository extends JpaRepository<WarehouseReceivedItems, Long> {

    List<WarehouseReceivedItems> findByProductId(Long productId);
}
