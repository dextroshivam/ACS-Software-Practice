package com.acs.acs.Repository;


import com.acs.acs.Enitities.OMSOrdersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OMSInfoRepository extends JpaRepository<OMSOrdersInfo,Long> {
}
