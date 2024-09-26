package com.acs.acs.Repository;

import com.acs.acs.Enitities.ProductAttributeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributeInfoRepository extends JpaRepository<ProductAttributeInfo, Long> {
    List<ProductAttributeInfo> findByProductId(Long id);

}
