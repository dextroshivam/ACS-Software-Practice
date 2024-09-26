package com.acs.acs.Enitities;

import com.acs.acs.ENUM.ProductCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Boolean status;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "SKU must be alphanumeric with underscores allowed")
    private String sku; // Alphanumeric with underscores allowed


    @Column(unique = true, nullable = false)
    private String upc;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    private Long createdBy;

    @NotNull(message = "Created On cannot be null")
//    @FutureOrPresent(message = "The date cannot be in the past")
    private LocalDateTime createdOn;
}

