package com.acs.acs.Repository;

import com.acs.acs.Enitities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountryId(Long countryId);

    // Find states by countryId
//    List<State> findByCountryId(Long countryId);
}

