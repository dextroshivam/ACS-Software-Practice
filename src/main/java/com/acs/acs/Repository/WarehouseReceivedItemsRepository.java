package com.acs.acs.Repository;

import com.acs.acs.Enitities.Product;
import com.acs.acs.Enitities.WarehouseReceivedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseReceivedItemsRepository extends JpaRepository<WarehouseReceivedItems, Long> {

    List<WarehouseReceivedItems> findByProductId(Long productId);
//    WarehouseReceivedItems findONeByProductId(Long productId);
//    Optional<WarehouseReceivedItems> findByProductId(Long productId);
    Optional<WarehouseReceivedItems> findOneByProductId(Long productId);
}
