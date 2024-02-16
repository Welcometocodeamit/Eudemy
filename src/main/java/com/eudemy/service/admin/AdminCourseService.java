package com.eudemy.service.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminCourseService {
	
	@Autowired CourseRepository courseRepository;
	@Autowired CategoryRepository categoryRepository;
	@Autowired ResponseWrapper wrapper;
	

	
//	add a course
	public ResponseEntity<?> addCourse(Course course){
		Category c = course.getCategory();
		System.out.println(c);
		if (c == null) {
			course.setCategory(null);
		}else {
			
			
//		if category not present but want to create
		if(c.getCategoryId()==0) {
			Category c0=new Category();
			c0.setCategoryTitle(c.getCategoryTitle());
			Category savedC0 =categoryRepository.save(c0);
			course.setCategory(savedC0);
			
//			if category present
		}else if(c.getCategoryId() > 0) {
			Category foundC=categoryRepository.findById(c.getCategoryId()).orElseThrow(()->{
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot found category with given information");
			});
			course.setCategory(foundC);
		}
		}
	
	
//		if category not present and not want to create
		
		Course savedCourse=courseRepository.save(course);
		if(savedCourse == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot add product");}
		wrapper.setMessage("Course added sucess");
		return new ResponseEntity<>(wrapper, HttpStatus.CREATED);
		
	}
		
	
//	delete course
	public ResponseEntity<?> deleteCourseById(int id){
		Optional<Course> foundCourse=courseRepository.findById(id);
		if(foundCourse.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
		}
		courseRepository.deleteById(id);
		wrapper.setMessage("Course deleted");
		return new ResponseEntity<>(wrapper, HttpStatus.OK);
	}
	
//	update course 
	public ResponseEntity<?> updateCourse(Course course){
		Course foundCourse=courseRepository.findById(course.getCourseId()).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found which is to update");
		});
		
		course.setCourseId(foundCourse.getCourseId());
//		course.setCategory(foundCourse.getCategory());
		Course updatedCourse=courseRepository.save(course);
		
		if(updatedCourse == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not updated");
		}
		wrapper.setMessage("Course updated");
		return new ResponseEntity<>(wrapper, HttpStatus.CREATED);
		
	}
	
//	assign category to courses
	public ResponseEntity<?> assignCategoryToCourse(int courseId, int categoryId){
		Course foundCourses=courseRepository.findById(courseId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Courses with provided id not found");
		});
		
		Category foundCategory=categoryRepository.findById(categoryId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with provided id not found");
		});
		
		foundCourses.setCategory(foundCategory);
		courseRepository.save(foundCourses);
		wrapper.setMessage(foundCourses.getCategory().getCategoryTitle()+" category assined to "+foundCourses.getCourseName());
		return new ResponseEntity<>(wrapper, HttpStatus.OK);
	}

}
