package com.eudemy.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.controller.user.UserCourseController;
import com.eudemy.models.Course;
import com.eudemy.models.OrderDefination;
import com.eudemy.models.Orders;
import com.eudemy.models.ResponseWrapper;
import com.eudemy.models.User;
import com.eudemy.repositories.CourseRepository;
import com.eudemy.repositories.OrderRepository;
import com.eudemy.repositories.UserRepository;

@Service
public class UserOrderService {
	
	@Autowired OrderRepository orderRepository;
	@Autowired CourseRepository courseRepository;
	@Autowired UserRepository userRepository;
	@Autowired User user;
	@Autowired UserCourseService userCourseService;
	@Autowired ResponseWrapper wrap;
	
	
//	create order
	public ResponseEntity<?> createOrder(int userId, OrderDefination orderDefination){
		Orders order = new Orders();
		order.setTotlePrice(orderDefination.getTotalPrice());
		User user = userRepository.findById(userId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
		});
		order.setUser(user);
		int[] courseIds = orderDefination.getCourseId();
		List<Course> courses = new ArrayList<>();
		for(int id : courseIds) {
			Course course=courseRepository.findById(id).orElse(null);
			courses.add(course);
			
			userCourseService.deleteUserFromCourse(userId, course.getCourseId());
		}
		order.setCourse(courses);
		order.setOrderDate(new Date());
		orderRepository.save(order);
		
		wrap.setMessage("order created");
		return new ResponseEntity<>(wrap, HttpStatus.OK);
	}
	
//	get order by user
	public ResponseEntity<?> getOrderByUser(int userId){
		user.setUserId(userId);
		List<Orders> orders =orderRepository.findByUser(user);
		if(orders.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order history is null");
		}
		
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

}
