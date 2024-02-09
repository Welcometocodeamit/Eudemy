package com.eudemy.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.Category;
import com.eudemy.models.Course;
import com.eudemy.repositories.CategoryRepository;
import com.eudemy.repositories.CourseRepository;

@Service
public class AdminCategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
//	add category
	public ResponseEntity<?> addCategory(Category category){
	  category.setCourse(null);
	  Category savedCategory=categoryRepository.save(category);
	  if(savedCategory == null) {
		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not saved");
	  }
	  
	  return new ResponseEntity<>("Category saved", HttpStatus.OK);
	  
	}
	
//	update category
	public ResponseEntity<?> updateCategory(Category category){
		Category foundCategory =categoryRepository.findById(category.getCategoryId()).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found which is to update");
		});
		category.setCategoryId(foundCategory.getCategoryId());
		categoryRepository.save(category);
		
		return new ResponseEntity<>("Category updated", HttpStatus.OK);
	}
	
//	delete category
	public ResponseEntity<?> deleteById(int id){
		Category foundCategory =categoryRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found which is to delete");
		});
		List<Course> foundCourses=courseRepository.findByCategory(foundCategory);
		
		foundCourses.forEach(course->{
			course.setCategory(null);
			courseRepository.save(course);
		});
		
		categoryRepository.deleteById(id);
		return new ResponseEntity<>("Category deleted", HttpStatus.OK);
	}
	

	


}
