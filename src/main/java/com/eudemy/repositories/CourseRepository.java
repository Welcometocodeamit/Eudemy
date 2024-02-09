package com.eudemy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.Category;
import com.eudemy.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	List<Course> findByCategory(Category category);

}
