package com.acs.acs.Repository;

import com.acs.acs.Enitities.ClientCarrierSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCarrierSelectionRepository extends JpaRepository<ClientCarrierSelection, Long> {}
