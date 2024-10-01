package com.acs.acs.Repository;

import com.acs.acs.Enitities.WarehouseOrdersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseOrdersInfoRepository  extends JpaRepository<WarehouseOrdersInfo, Long> {
}
