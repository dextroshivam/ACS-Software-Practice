package com.acs.acs.Services;

import com.acs.acs.DTO.RequestDTO.WarehouseBoxRequest.WarehouseBoxRequestDTO;
import com.acs.acs.Enitities.WarehouseBoxLabel;
import com.acs.acs.Repository.WarehouseBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseBoxService {

  @Autowired WarehouseBoxRepository warehouseBoxRepository;

  public ResponseEntity<?> getBoxes(WarehouseBoxRequestDTO warehouseBoxDTO) {

    List<Long> response = new ArrayList<>();

    // also include query for more optimized way
    //     get available boxes
//        List<WarehouseBoxLabel> boxes =
//            warehouseBoxRepository.findByWarehouseIdAndBoxTypeAndBoxStatus(
//                warehouseBoxDTO.getWarehouseId(), warehouseBoxDTO.getBoxType(), true);

//    List<WarehouseBoxLabel> boxes =
//        warehouseBoxRepository.findRequiredBoxes(
//            warehouseBoxDTO.getWarehouseId(), warehouseBoxDTO.getBoxType(),
//                true,warehouseBoxDTO.getQuantity());
    Pageable pageable = PageRequest.of(0,warehouseBoxDTO.getQuantity());
    List<WarehouseBoxLabel> boxes =
        warehouseBoxRepository.findRequiredBoxes(
            warehouseBoxDTO.getWarehouseId(), warehouseBoxDTO.getBoxType(),
                true,pageable);

    System.out.println("Boxes List : " + boxes);
    // analyse the list
    if (boxes.isEmpty()) {
      // NO boxes available
      return ResponseEntity.notFound().eTag("No boxes available with required BoxType").build();
    } else if (boxes.size() < warehouseBoxDTO.getQuantity()) {
      // Not enough boxes availble
      String etag = "There are Only " + boxes.size() + " boxes available with required BoxType";
      return ResponseEntity.notFound().eTag(etag).build();
    }
    for (int i = 0; i < warehouseBoxDTO.getQuantity(); i++) {
      WarehouseBoxLabel box = boxes.get(i);
      response.add(box.getId());
      box.setBoxStatus(false);
      warehouseBoxRepository.save(box);
    }
    return ResponseEntity.ok(response);
  }
}
