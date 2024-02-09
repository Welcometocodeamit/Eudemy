package com.eudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
