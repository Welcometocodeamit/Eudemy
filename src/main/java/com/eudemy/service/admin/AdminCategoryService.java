package com.eudemy.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.Category;
import com.eudemy.models.Course;
import com.eudemy.models.ResponseWrapper;
import com.eudemy.repositories.CategoryRepository;
import com.eudemy.repositories.CourseRepository;

@Service
public class AdminCategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CourseRepository courseRepository;
	@Autowired ResponseWrapper wrap;
	
//	add category
	public ResponseEntity<?> addCategory(Category category){
	  category.setCourse(null);
	  Category savedCategory=categoryRepository.save(category);
	  if(savedCategory == null) {
		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not saved");
	  }
	  wrap.setMessage("Category saved");
	  return new ResponseEntity<>(wrap, HttpStatus.OK);
	  
	}
	
//	update category
	public ResponseEntity<?> updateCategory(Category category){
		Category foundCategory =categoryRepository.findById(category.getCategoryId()).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found which is to update");
		});
		category.setCategoryId(foundCategory.getCategoryId());
		categoryRepository.save(category);
		wrap.setMessage("Category updated");
		return new ResponseEntity<>(wrap, HttpStatus.OK);
	}
	
//	delete category
	public ResponseEntity<?> deleteById(int id){
		Category foundCategory =categoryRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found which is to delete");
		});
		Pageable page = PageRequest.of(0, 20);
		Page<Course> foundCourses=courseRepository.findByCategory(foundCategory, page);
		
		foundCourses.getContent().forEach(course->{
			course.setCategory(null);
			courseRepository.save(course);
		});
		
		categoryRepository.deleteById(id);
		wrap.setMessage("Category deleted");
		return new ResponseEntity<>(wrap, HttpStatus.OK);
	}
	

	


}
