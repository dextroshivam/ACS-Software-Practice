package com.acs.acs.Repository;

import com.acs.acs.Enitities.WarehouseOrdersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseOrdersInfoRepository  extends JpaRepository<WarehouseOrdersInfo, Long> {
    Optional<WarehouseOrdersInfo> findByOrderNumber(String orderNumber);
}
