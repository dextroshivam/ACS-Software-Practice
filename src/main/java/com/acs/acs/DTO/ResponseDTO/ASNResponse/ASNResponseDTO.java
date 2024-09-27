package com.acs.acs.DTO.ResponseDTO.ASNResponse;

import com.acs.acs.DTO.RequestDTO.ASNRequest.ASNUnitRequestDTO;
import com.acs.acs.Enitities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASNResponseDTO {
    private Long asnId;
    private Long userId;
    private Long warehouseId;
    List<ASNUnitRequestDTO> units;
}
