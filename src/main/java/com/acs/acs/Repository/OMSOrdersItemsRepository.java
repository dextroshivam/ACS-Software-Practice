package com.acs.acs.Repository;

import com.acs.acs.Enitities.OMSOrdersItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OMSOrdersItemsRepository extends JpaRepository<OMSOrdersItems,Long> {
}
