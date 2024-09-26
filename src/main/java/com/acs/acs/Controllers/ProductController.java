package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductAttributeRequestDTO;
import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductRequestDTO;
import com.acs.acs.DTO.ResponseDTO.ProductResponse.ProductResponseDTO;
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
        try{
            Product createdProduct = productService.createProduct(productRequestDTO);
            return ResponseEntity.ok(createdProduct);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // 2. Get product by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = productService.getProductById(id);
        if (productResponseDTO != null) {
            return ResponseEntity.ok(productResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // 3. Get all products
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String sku,@RequestParam(required = false) Long customerId,@RequestParam(required = false) Long userId) {
        try{
            List<Product> productList=productService.searchProducts(sku,customerId,userId);
            return ResponseEntity.ok(productList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }



    }
}

