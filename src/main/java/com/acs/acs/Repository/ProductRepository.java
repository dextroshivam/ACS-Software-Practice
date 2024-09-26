package com.acs.acs.Repository;

import com.acs.acs.Enitities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional custom queries (if needed) can be added here
}
