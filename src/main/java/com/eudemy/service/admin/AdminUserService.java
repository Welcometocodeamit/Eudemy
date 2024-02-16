package com.eudemy.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.CourseFeedback;
import com.eudemy.models.Orders;
import com.eudemy.models.ResponseWrapper;
import com.eudemy.models.User;
import com.eudemy.repositories.CourseFeedbackRepository;
import com.eudemy.repositories.OrderRepository;
import com.eudemy.repositories.UserRepository;

@Service
public class AdminUserService {
	
	@Autowired UserRepository userRepository;
	@Autowired ResponseWrapper wrapper;
	@Autowired CourseFeedbackRepository courseFeedbackRepository;
	@Autowired OrderRepository orderRepository;
	
//	get all users
	public ResponseEntity<?> getAllUsers(){
		List<User> users=userRepository.findAll();
		if(users.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found");
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
//	delete user by id
	public ResponseEntity<?> deleteUserById(int userId){
		
		User user=userRepository.findById(userId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		});
		
		List<CourseFeedback> foundFeedback=courseFeedbackRepository.findByUser(user);
		foundFeedback.forEach(feedback->{
			courseFeedbackRepository.deleteById(feedback.getCourseFeedbackId());
		});
		
		List<Orders> order = orderRepository.findByUser(user);
		order.forEach(o->{
		    Orders foundOrder=orderRepository.findById(o.getId()).orElse(null);
		    foundOrder.setCourse(null);
		    orderRepository.save(foundOrder);
			orderRepository.deleteById(o.getId());
		});
		
		userRepository.deleteById(userId);
		wrapper.setMessage("User deleted");
		return new ResponseEntity<>(wrapper, HttpStatus.OK);
	}

}
