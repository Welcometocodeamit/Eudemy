package com.eudemy.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.ResponseWrapper;
import com.eudemy.repositories.CourseFeedbackRepository;
import com.eudemy.service.user.UserFeedbackService;

@Service
public class AdminFeedbackService {
	
	@Autowired UserFeedbackService userFeedbackService;
	@Autowired CourseFeedbackRepository courseFeedbackRepository;
	@Autowired ResponseWrapper wrapper;
	
//	delete feedback
	public ResponseEntity<?> deleteFeedback(int feedbackId){
		courseFeedbackRepository.findById(feedbackId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found");
		});
		courseFeedbackRepository.deleteById(feedbackId);
		wrapper.setMessage("Feedback deleted");
		return new ResponseEntity<>(wrapper, HttpStatus.OK);
	}

}
