package com.acs.acs.Repository;

import com.acs.acs.Enitities.WarehouseOrdersItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseOrdersItemsRepository extends JpaRepository<WarehouseOrdersItems, Long> {

    List<WarehouseOrdersItems> findByWarehouseOrderInfoId(Long warehouseOrderInfoId);
}
