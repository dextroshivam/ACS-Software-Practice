package com.acs.acs.Repository;

import com.acs.acs.Enitities.AdvanceShipmentNoticeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvanceShipmentNoticeUnitRepository extends JpaRepository<AdvanceShipmentNoticeUnit, Long> {
}
