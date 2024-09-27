package com.acs.acs.DTO.RequestDTO.ASNRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASNRequestDTO {

    private ASNInfoRequestDTO asnInfoRequestDTO;
    private List<ASNUnitRequestDTO> units;
}
