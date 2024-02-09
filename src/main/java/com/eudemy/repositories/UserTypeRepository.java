package com.eudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

}
