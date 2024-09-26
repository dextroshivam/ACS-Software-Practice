package com.acs.acs.Repository;


import com.acs.acs.Enitities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    // Find country by shortName (if needed)
//    Country findByShortName(String shortName);
}
