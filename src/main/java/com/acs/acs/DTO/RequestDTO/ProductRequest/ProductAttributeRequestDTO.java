package com.acs.acs.DTO.RequestDTO.ProductRequest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeRequestDTO {

    @NotNull(message = "Attribute Id can't be null")
    private Long attributeId;
    @NotNull(message = "Attribute Description can't be null")
    private String attributeDescription;
}
