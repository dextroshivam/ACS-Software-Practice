package com.acs.acs.Services;


import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductRequestDTO;
import com.acs.acs.Enitities.Product;
import com.acs.acs.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productRequestDTOToProduct(productRequestDTO);
        productRepository.save(product);
        return product;
    }


    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

//    @Override
//    public List<ProductDTO> getAllProducts() {
//        return productRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
//    }
//
//    @Override
//    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
//        Optional<Product> existingProduct = productRepository.findById(id);
//        if (existingProduct.isPresent()) {
//            Product product = existingProduct.get();
//            product.setName(productDTO.getName());
//            product.setDescription(productDTO.getDescription());
//            product.setSku(productDTO.getSku());
//            product.setUpc(productDTO.getUpc());
//            product.setStatus(productDTO.getStatus());
//            product.setCreatedBy(productDTO.getCreatedBy());
//            product.setCategory(productDTO.getCategory());
//            product.setCreatedOn(productDTO.getCreatedOn());
//            productRepository.save(product);
//            return mapToDTO(product);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean deleteProduct(Long id) {
//        if (productRepository.existsById(id)) {
//            productRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
//    private ProductDTO mapToDTO(Product product) {
//        return new ProductDTO(product.getId(), product.getClientId(), product.getStatus(), product.getSku(),
//                product.getUpc(), product.getName(), product.getDescription(),
//                product.getCategory(), product.getCreatedBy(), product.getCreatedOn());
//    }
//
//    private Product mapToEntity(ProductDTO productDTO) {
//        return new Product(productDTO.getId(), productDTO.getClientId(), productDTO.getStatus(), productDTO.getSku(),
//                productDTO.getUpc(), productDTO.getName(), productDTO.getDescription(),
//                productDTO.getCategory(), productDTO.getCreatedBy(), productDTO.getCreatedOn());
//    }
public Product productRequestDTOToProduct(ProductRequestDTO productRequestDTO) {
    Product product = new Product();

    product.setCustomerId(productRequestDTO.getCustomerId());
    product.setSku(productRequestDTO.getSku());
    product.setUpc(productRequestDTO.getUpc());
    product.setName(productRequestDTO.getName());
    product.setDescription(productRequestDTO.getDescription());
    product.setCategory(productRequestDTO.getCategory());
    product.setCreatedBy(productRequestDTO.getCreatedBy());
//    product.setCreatedOn(productRequestDTO.getCreatedOn());
    product.setCreatedOn(new Date());
    // Default or null fields
    product.setStatus(true); // Defaulting status to true (you can adjust this if necessary)
    return product;
}

}
