package com.acs.acs.Repository;

import com.acs.acs.Enitities.WarehouseReceivedItemLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseReceivedItemLogsRepository extends JpaRepository<WarehouseReceivedItemLogs, Long> {
}
