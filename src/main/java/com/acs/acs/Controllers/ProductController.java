package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductRequestDTO;
import com.acs.acs.Enitities.Product;
import com.acs.acs.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private  ProductService productService;

    // 1. Create product
    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        Product createdProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(createdProduct);
    }

    // 2. Get product by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

//    // 3. Get all products
//    @GetMapping
//    public ResponseEntity<List<ProductDTO>> getAllProducts() {
//        List<ProductDTO> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    // 4. Update product details
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
//        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
//        if (updatedProduct != null) {
//            return ResponseEntity.ok(updatedProduct);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    // 5. Delete product
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        boolean isDeleted = productService.deleteProduct(id);
//        if (isDeleted) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}

