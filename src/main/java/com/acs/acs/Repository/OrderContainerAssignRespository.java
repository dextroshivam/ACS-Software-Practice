package com.acs.acs.Repository;

import com.acs.acs.ENUM.OrderStatus;
import com.acs.acs.Enitities.OrderContainerAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderContainerAssignRespository extends JpaRepository<OrderContainerAssign, Long> {
    List<OrderContainerAssign> findByContainerIdAndOrderNumber(Long containerId, String orderNumber);
    OrderContainerAssign findByOrderNumberAndProductIdAndQuantityAndContainerId(String orderNumber,Long productId,Long quantity,Long containerId);

    OrderContainerAssign findByBoxIdAndStatus(Long boxLabel,OrderStatus status);

    OrderContainerAssign findByOrderNumberAndBoxId(String orderNumber, Long boxId);

    OrderContainerAssign findByOrderNumberAndBoxIdAndStatus(String orderNumber, Long boxId, OrderStatus orderStatus);
}
