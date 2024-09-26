package com.acs.acs.DTO.ResponseDTO.AttributesResponse;

import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductAttributeRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributesResponseDTO {
    List<ProductAttributeRequestDTO> attributesList;
}
