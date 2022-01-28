package com.UserProduct.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserProduct.Model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Optional<Users> findByRole(String Role);
}


/*
import java.util.List;
import java.util.Optional;

Users findByUsername(String username);

Optional<Users> findByUsername(String username);

public List<Users> findAll();

public Optional<Users> findById(Long id);

public Users getById(Long id);

Boolean existsByUsername(String username);
*/
