package com.eudemy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.service.admin.AdminFeedbackService;
import com.eudemy.service.user.UserFeedbackService;

@RestController
@RequestMapping("user")
public class AdminFeedbackController {
	
	@Autowired private AdminFeedbackService adminFeedbackService;
	
	@Autowired UserFeedbackService userFeedbackService;
	
//	@GetMapping("/feedback/{courseId}")
//	public ResponseEntity<?> getFeedbackByCourse(@PathVariable int courseId){
//		ResponseEntity<?> feedbackResponse=userFeedbackService.getFeedBackByCourse(courseId);
//		return new ResponseEntity<>(feedbackResponse, HttpStatus.OK);
//	}
	
	@DeleteMapping("/feedback/{feedbackId}")
	public ResponseEntity<?> deleteById(@PathVariable int feedbackId){
		return adminFeedbackService.deleteFeedback(feedbackId);
	}

}
