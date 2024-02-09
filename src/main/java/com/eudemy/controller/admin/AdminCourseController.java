package com.eudemy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.models.Course;
import com.eudemy.service.admin.AdminCourseService;
import com.eudemy.service.user.UserCourseService;


@RestController
@RequestMapping("/admin")
public class AdminCourseController {
	
	@Autowired 
	private AdminCourseService adminService;
	
	@Autowired
	private UserCourseService userService;
	
//	get all courses
	@GetMapping("/courses")
	public ResponseEntity<?> getAllCourse(){
		return userService.getCourses();
	}
	
//	add course
	@PostMapping("/courses")
	public ResponseEntity<?> addCourse(@RequestBody Course course){
		return adminService.addCourse(course);
	}
	
//	delete course
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<?> deleteCourseById(@PathVariable int courseId){
		return adminService.deleteCourseById(courseId);
	}
	
//	Update course
	@PutMapping("/courses")
	public ResponseEntity<?> updateCourse(@RequestBody Course course){
		return adminService.updateCourse(course);
	}
	
//	assign category to course
	@PutMapping("/courseId={courseId}/categoryId={categoryId}")
	public ResponseEntity<?> assignCategoryToCourse(@PathVariable int courseId, @PathVariable int categoryId){
		return adminService.assignCategoryToCourse(courseId, categoryId);
	}
}
