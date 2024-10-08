package com.acs.acs.Repository;

import com.acs.acs.Enitities.PickerAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickerAssignRepository extends JpaRepository<PickerAssign, Long> {}
