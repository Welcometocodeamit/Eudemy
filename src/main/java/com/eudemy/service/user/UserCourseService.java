package com.eudemy.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.Category;
import com.eudemy.models.Course;
import com.eudemy.models.Orders;
import com.eudemy.models.ResponseWrapper;
import com.eudemy.models.Token;
import com.eudemy.models.User;
import com.eudemy.repositories.CategoryRepository;
import com.eudemy.repositories.CourseRepository;
import com.eudemy.repositories.OrderRepository;
import com.eudemy.repositories.UserRepository;

@Service
public class UserCourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	@Autowired UserRepository userRepository;
	
	@Autowired ResponseWrapper wrapper;
	
	@Autowired OrderRepository orderRepository;
	
	@Autowired Token t;
	
	
	
//	get all courses
	public ResponseEntity<?> getCourses(int page){
		Pageable paging =  PageRequest.of(page, 8);
		Page<Course> courses= courseRepository.findAll(paging);
		if(courses.getSize()==0) {
			return new ResponseEntity<>("Courses not found, please add some", HttpStatus.OK);
		}
		User u= new User();
		u.setUserId(t.getUserId());
		List<Orders> order=orderRepository.findByUser(u);
		courses.getContent().forEach(course->{
			order.forEach(or->{
				or.getCourse().forEach(data->{
					if(data.getCourseId()==course.getCourseId()) {
						course.setFe(true);
					}
				});
			});
		});
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
//	assign course to user
	public ResponseEntity<?> addCourse(int userId, int courseId){
		User user=userRepository.findById(userId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		});
		
		Course course=courseRepository.findById(courseId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
		});
		
		user.getCourse().add(course);
		userRepository.save(user);
		wrapper.setMessage("Course assigned to user");
		return new ResponseEntity<>(wrapper, HttpStatus.OK);
	}
	
//	delete user from course
	public ResponseEntity<?> deleteUserFromCourse(int userId, int courseId){
		User user=userRepository.findById(userId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		});
		
		Course course=courseRepository.findById(courseId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
		});
		
		user.getCourse().remove(course);
		userRepository.save(user);
		wrapper.setMessage("Course deleted from users cart");
		return new ResponseEntity<>(wrapper, HttpStatus.OK);
	}
	
//get courses by category
	public ResponseEntity<?> getCourseByCategoryId(int categoryId, int pno){
		Category category = new Category();
		category.setCategoryId(categoryId);
		Pageable page = PageRequest.of(pno, 8);
		Page<Course> courses= courseRepository.findByCategory(category, page);
		if(courses.getSize()==0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with given category not found");
		}
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
//	get courses by name
	public ResponseEntity<?> getCourseByName(String courseName){
		List<Course> course=courseRepository.findByCourseName(courseName);
		if(course.size()==0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with given name");
		}
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
//	get course by courseID
	public ResponseEntity<?> getCourseByCourseId(int courseId){
		Course course=courseRepository.findById(courseId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with given id");
		});
		User u= new User();
		u.setUserId(t.getUserId());
		List<Orders> order=orderRepository.findByUser(u);
		order.forEach(or->{
			or.getCourse().forEach(data->{
				if(data.getCourseId()==course.getCourseId()) {
					course.setFe(true);
				}
			});
		});
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}


}
