package com.eudemy.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.Course;
import com.eudemy.models.CourseFeedback;
import com.eudemy.models.JwtResponse;
import com.eudemy.models.ResponseWrapper;
import com.eudemy.models.Token;
import com.eudemy.models.User;
import com.eudemy.repositories.CourseFeedbackRepository;
import com.eudemy.security.JwtHelper;

@Service
public class UserFeedbackService {
	
	@Autowired CourseFeedbackRepository courseFeedbackRepository;
	
	@Autowired Course course;
	
	@Autowired User user;
	
	@Autowired CourseFeedback courseFeedback;
	
	@Autowired ResponseWrapper wrapper;
	
	@Autowired Token t;
	
	@Autowired JwtHelper jwtHelper;
	
	
//	get all feedback by course id
	public ResponseEntity<?> getFeedBackByCourse(int courseId){
		course.setCourseId(courseId);
		List<CourseFeedback> feedbacks=courseFeedbackRepository.findByCourse(course);
		feedbacks.forEach(feedback->{
			if(feedback.getUser().getUserId()==t.getUserId()) {
				feedback.setAuthority(true);
			}
			System.out.println(jwtHelper.isAdmin);
			if(jwtHelper.isAdmin) {
				feedback.setAuthority(true);
			}
			
			
		});
		if(feedbacks.size()==0) {
			return new ResponseEntity<>(feedbacks, HttpStatus.OK);
		}
		return new ResponseEntity<>(feedbacks, HttpStatus.OK);
	}
	
//	add feedback to course
	public ResponseEntity<?> addFeedback(int courseId, int userId, String feedback){
		CourseFeedback feedback2 = new CourseFeedback();
		course.setCourseId(courseId);
		user.setUserId(userId);
		feedback2.setUser(user);
		feedback2.setCourse(course);
		feedback2.setCourseFeedback(feedback);
		
		CourseFeedback addedFeedback=courseFeedbackRepository.save(feedback2);
		
		if(addedFeedback == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Feedback not added");
		}
		wrapper.setMessage("Feedback added");
		return new ResponseEntity<>(wrapper, HttpStatus.OK);	
		
	}

}
