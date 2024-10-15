package com.acs.acs.Repository;

import com.acs.acs.Enitities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {}
