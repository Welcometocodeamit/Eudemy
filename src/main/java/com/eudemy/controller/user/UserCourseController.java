package com.eudemy.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.models.Token;
import com.eudemy.service.user.UserCourseService;

@RestController
@RequestMapping("/user")
public class UserCourseController {
	
	@Autowired
	private UserCourseService userService;
	
	@Autowired Token t;
	
	@GetMapping("/courses/pageNo={page}")
	public ResponseEntity<?> getAllCourses(@PathVariable int page){
		return userService.getCourses(page);
	}
	
	@GetMapping("/courses/{categoryId}/pageNo={pageNo}")
	public ResponseEntity<?> getCourseByCategoryId(@PathVariable int categoryId, @PathVariable int pageNo){
		return userService.getCourseByCategoryId(categoryId, pageNo);
	}
	
	@GetMapping("/courses/courseName={courseName}")
	public ResponseEntity<?> getCourseByCourseName(@PathVariable String courseName){
		return userService.getCourseByName(courseName);
	}
	
	@PutMapping("/course/{courseId}")
	public ResponseEntity<?> assignCourseToUser(@PathVariable int courseId){
		return userService.addCourse(t.getUserId(), courseId);
	}
	
	@GetMapping("/course/courseId={courseId}")
	public ResponseEntity<?> getCourseByCourseId (@PathVariable int courseId){
		return userService.getCourseByCourseId(courseId);
	}
	
	@DeleteMapping("/course/{courseId}")
	public ResponseEntity<?> deleteCourseFromUser(@PathVariable int courseId){
		return userService.deleteUserFromCourse(t.getUserId(), courseId);
	}

}
