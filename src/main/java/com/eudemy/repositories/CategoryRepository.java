package com.eudemy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findByCategoryTitle(String name);

}
