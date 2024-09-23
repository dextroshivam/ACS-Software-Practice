package com.acs.acs.Repository;


import com.acs.acs.Enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
