package com.acs.acs.Repository;

import com.acs.acs.Enitities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByRoleId(Long id);

//    @Query("SELECT p FROM Permission p WHERE p.roleId = :roleId")
//    List<Permission> findByRoleId(@Param("roleId") Long roleId);
}
