package com.acs.acs.Repository;

import com.acs.acs.Enitities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
