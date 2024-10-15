package com.acs.acs.Repository;

import com.acs.acs.Enitities.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnersRepository extends JpaRepository<Partners, Long> {
    Optional<Partners> findByCustomerId(Long customerId);

    Partners findByName(String carrierName);
}
