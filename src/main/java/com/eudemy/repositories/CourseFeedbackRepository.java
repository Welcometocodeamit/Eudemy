package com.eudemy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.Course;
import com.eudemy.models.CourseFeedback;
import com.eudemy.models.User;

public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Integer> {
	
	List<CourseFeedback> findByCourse(Course course);
	
	List<CourseFeedback> findByUser(User user);

}