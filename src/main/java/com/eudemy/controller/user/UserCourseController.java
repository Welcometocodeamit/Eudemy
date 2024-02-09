package com.eudemy.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.service.user.UserCourseService;

@RestController
@RequestMapping("/user")
public class UserCourseController {
	
	@Autowired
	private UserCourseService userService;
	
	@GetMapping("/courses")
	public ResponseEntity<?> getAllCourses(){
		return userService.getCourses();
	}
	

}
