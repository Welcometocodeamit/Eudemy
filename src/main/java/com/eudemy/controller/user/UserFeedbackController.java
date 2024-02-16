package com.eudemy.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.models.CourseFeedback;
import com.eudemy.models.Token;
import com.eudemy.service.user.UserFeedbackService;

@RestController
@RequestMapping("user")
public class UserFeedbackController {
	
	@Autowired UserFeedbackService userFeedCourseService;
	@Autowired Token t;
	
//	get feedback by course
	@GetMapping("/feedback/{courseId}")
	public ResponseEntity<?> getFeedbackByCourseId(@PathVariable int courseId){
		return userFeedCourseService.getFeedBackByCourse(courseId);
	}
	
	@PostMapping("/feedback/course/{courseId}")
	public ResponseEntity<?> postFeedback(@PathVariable int courseId, @RequestBody CourseFeedback courseFeedback){
		return userFeedCourseService.addFeedback(courseId, t.getUserId(), courseFeedback.getCourseFeedback());
	}
}
