package com.acs.acs.Repository;

import com.acs.acs.Enitities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySkuAndCustomerId(String sku, Long customerId);
    Product findBySku(String sku);

    List<Product> findBySkuAndCreatedBy(String sku, Long createdBy);

    List<Product> findByCreatedByAndCustomerId(Long createdBy, Long customerId);

    List<Product> findByCreatedByOrCustomerIdOrSku(Long userId,Long customerId,String sku);
    // Additional custom queries (if needed) can be added here
}
