package com.acs.acs.Repository;

import com.acs.acs.Enitities.AdvanceShipmentNoticeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvanceShipmentNoticeInfoRepository extends JpaRepository<AdvanceShipmentNoticeInfo, Long> {
}
