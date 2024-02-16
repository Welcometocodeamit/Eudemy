package com.eudemy.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.Category;
import com.eudemy.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>  {
	
	Page<Course> findByCategory(Category category, Pageable page);
	
	List<Course> findByCourseName(String courseName);
}
