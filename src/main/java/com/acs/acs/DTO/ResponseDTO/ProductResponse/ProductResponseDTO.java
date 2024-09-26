package com.acs.acs.DTO.ResponseDTO.ProductResponse;

import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductAttributeRequestDTO;
import com.acs.acs.DTO.ResponseDTO.AttributesResponse.AttributesResponseDTO;
import com.acs.acs.Enitities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponseDTO {
    Product product;
    List<ProductAttributeRequestDTO> attributesList;
}
