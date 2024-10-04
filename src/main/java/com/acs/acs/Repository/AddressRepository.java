package com.acs.acs.Repository;

import com.acs.acs.Enitities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
  Optional<Address> findByCityIdAAndStateIdAndCountyIdAndZipCode(
      Long stateId, Long cityId, Long countyId, String zipCode);
}
