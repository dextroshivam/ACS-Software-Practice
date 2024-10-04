package com.acs.acs.Repository;

import com.acs.acs.Enitities.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnersRepository extends JpaRepository<Partners, Long> {}
