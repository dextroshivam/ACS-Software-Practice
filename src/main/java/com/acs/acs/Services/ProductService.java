package com.acs.acs.Services;


import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductAttributeRequestDTO;
import com.acs.acs.DTO.RequestDTO.ProductRequest.ProductRequestDTO;
import com.acs.acs.DTO.ResponseDTO.AttributesResponse.AttributesResponseDTO;
import com.acs.acs.DTO.ResponseDTO.ProductResponse.ProductResponseDTO;
import com.acs.acs.Enitities.Product;
import com.acs.acs.Enitities.ProductAttributeInfo;
import com.acs.acs.Repository.ProductAttributeInfoRepository;
import com.acs.acs.Repository.ProductRepository;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductAttributeInfoRepository productAttributeInfoRepository;

    public Product createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productRequestDTOToProduct(productRequestDTO);
        Product savedProduct=productRepository.save(product);

        List<ProductAttributeRequestDTO> productAttributeRequestDTOList=productRequestDTO.getProductsAtrributes();
        for(ProductAttributeRequestDTO productAttributeRequestDTO:productAttributeRequestDTOList){
            ProductAttributeInfo productAttributeInfo=new ProductAttributeInfo();
            productAttributeInfo.setProductId(savedProduct.getId());
            productAttributeInfo.setAttributeId(productAttributeRequestDTO.getAttributeId());
            productAttributeInfo.setAttributeDescription(productAttributeRequestDTO.getAttributeDescription());
            productAttributeInfoRepository.save(productAttributeInfo);
        }
        return product;
    }

    public ProductResponseDTO getProductById(Long id) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//        Product product;
//        AttributesResponseDTO attributes;
        Optional<Product> product = productRepository.findById(id);
        productResponseDTO.setProduct(product.get());

//        make AttributesResponseDTO
        AttributesResponseDTO attributesResponseDTO = new AttributesResponseDTO();
        List<ProductAttributeRequestDTO> attributesList=new ArrayList<>();

        List<ProductAttributeInfo> productAttributeInfoList=productAttributeInfoRepository.findByProductId(id);
        for(ProductAttributeInfo p:productAttributeInfoList){
            ProductAttributeRequestDTO productAttributeRequestDTO=new ProductAttributeRequestDTO();
            productAttributeRequestDTO.setAttributeId(p.getAttributeId());
            productAttributeRequestDTO.setAttributeDescription(p.getAttributeDescription());
            attributesList.add(productAttributeRequestDTO);
        }
//        attributesResponseDTO.setAttributesList(attributesList);

        productResponseDTO.setAttributesList(attributesList);

        return productResponseDTO;
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOList=new ArrayList<>();
        List<Product> productList=productRepository.findAll();

        for (Product product : productList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProduct(product);
            //        make AttributesResponseDTO
            AttributesResponseDTO attributesResponseDTO = new AttributesResponseDTO();
            List<ProductAttributeRequestDTO> attributesList = new ArrayList<>();

            List<ProductAttributeInfo> productAttributeInfoList = productAttributeInfoRepository.findByProductId(product.getId());
            for (ProductAttributeInfo p : productAttributeInfoList) {
                ProductAttributeRequestDTO productAttributeRequestDTO = new ProductAttributeRequestDTO();
                productAttributeRequestDTO.setAttributeId(p.getAttributeId());
                productAttributeRequestDTO.setAttributeDescription(p.getAttributeDescription());
                attributesList.add(productAttributeRequestDTO);
            }
//        attributesResponseDTO.setAttributesList(attributesList);

            productResponseDTO.setAttributesList(attributesList);

            productResponseDTOList.add(productResponseDTO);
        }

        return productResponseDTOList;

    }

    public List<Product> searchProducts(String sku,Long customerId,Long userId){
        List<Product> productList;
        if (sku !=null && customerId!=null) {
            productList=productRepository.findBySkuAndCustomerId(sku,customerId);
        }else
        if(sku!=null && userId!=null){
            productList=productRepository.findBySkuAndCreatedBy(sku,userId);
        }else
        if(userId!=null && customerId!=null){
            productList=productRepository.findByCreatedByAndCustomerId(userId,customerId);
        }else if(userId!=null || customerId!=null || sku!=null){
            productList=productRepository.findByCreatedByOrCustomerIdOrSku(userId,customerId,sku);
        }

        else {
            productList=productRepository.findAll();
        }
        return productList;

    }



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
    product.setCreatedOn(LocalDateTime.now());
    // Default or null fields
    product.setStatus(true); // Defaulting status to true (you can adjust this if necessary)
    return product;
}

}
