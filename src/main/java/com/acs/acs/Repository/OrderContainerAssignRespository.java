package com.acs.acs.Repository;

import com.acs.acs.Enitities.OrderContainerAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderContainerAssignRespository extends JpaRepository<OrderContainerAssign, Long> {

}
