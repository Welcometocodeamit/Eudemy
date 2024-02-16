package com.eudemy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByFirstName(String username);
	
	Optional<User>  findByEmail(String email);

}
