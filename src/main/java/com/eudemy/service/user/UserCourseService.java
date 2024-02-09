package com.eudemy.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eudemy.models.Course;
import com.eudemy.repositories.CourseRepository;

@Service
public class UserCourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	
	
//	get all courses
	public ResponseEntity<?> getCourses(){
		List<Course> courses=courseRepository.findAll();
		if(courses.size()==0) {
			return new ResponseEntity<>("Courses not found, please add some", HttpStatus.OK);
		}
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	


}
