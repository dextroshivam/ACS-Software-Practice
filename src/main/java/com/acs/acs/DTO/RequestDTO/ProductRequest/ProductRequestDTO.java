package com.acs.acs.DTO.RequestDTO.ProductRequest;

import com.acs.acs.ENUM.ProductCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotNull(message = "Client ID cannot be null")
    private Long customerId;

    @NotBlank(message = "SKU cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "SKU must be alphanumeric with underscores allowed")
    private String sku; // Alphanumeric with underscores allowed

    @NotBlank(message = "UPC cannot be blank")
    @Size(min = 12, max = 12, message = "UPC must be exactly 12 characters")
    private String upc;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category cannot be null")
    private ProductCategory category; // Enum as String

    @NotNull(message = "Created By cannot be null")
    private Long createdBy;

    List<ProductAttributeRequestDTO> productsAtrributes;

}
