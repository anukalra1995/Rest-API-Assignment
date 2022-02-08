package com.UserProductSecurity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserProductSecurity.Model.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Optional<Users> findByRole(String Role);
}
