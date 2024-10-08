package com.acs.acs.Repository;

import com.acs.acs.ENUM.BoxType;
import com.acs.acs.Enitities.WarehouseBoxLabel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseBoxRepository extends JpaRepository<WarehouseBoxLabel, Long> {
//  @Query(
//      value =
//          "SELECT * FROM wh_box_label WHERE warehouse_id = :warehouseId AND box_type = :boxType AND box_status = :boxStatus LIMIT :count",
//      nativeQuery = true)
//  List<WarehouseBoxLabel> findRequiredBoxes(
//      @Param("warehouseId") Long warehouseId,
//      @Param("boxType") BoxType boxType,
//      @Param("boxStatus") Boolean boxStatus,
//      @Param("count") Integer count);
//@Query(
//        value = "SELECT * FROM wh_box_label WHERE warehouse_id = :warehouseId AND box_type = :boxType AND box_status = :boxStatus",
//        nativeQuery = true)

  @Query(
          "SELECT w FROM WarehouseBoxLabel w WHERE w.warehouseId = :warehouseId AND w.boxType = :boxType AND w.boxStatus = :boxStatus"
  )
List<WarehouseBoxLabel> findRequiredBoxes(
        @Param("warehouseId") Long warehouseId,
        @Param("boxType") BoxType boxType,
        @Param("boxStatus") Boolean boxStatus,
        Pageable pageable);

  List<WarehouseBoxLabel> findByWarehouseIdAndBoxTypeAndBoxStatus(
      Long warehouseId, BoxType boxType, Boolean boxStatus);
}
