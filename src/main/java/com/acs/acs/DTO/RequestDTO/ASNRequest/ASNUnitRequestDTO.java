package com.acs.acs.DTO.RequestDTO.ASNRequest;

import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductRequestDTO;
import com.acs.acs.Enitities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASNUnitRequestDTO {
    private Long productId;
    private Integer quantity;
}
