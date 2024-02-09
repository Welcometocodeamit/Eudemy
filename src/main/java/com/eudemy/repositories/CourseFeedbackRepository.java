package com.eudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.CourseFeedback;

public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Integer> {

}
