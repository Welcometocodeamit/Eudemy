package com.eudemy.models;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class CourseFeedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseFeedbackId;
	
	private String courseFeedback;
	
	private boolean authority;
	
	@ManyToOne
	@JoinColumn(name = "course")
	@JsonIgnore
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "user")
	@JsonIgnoreProperties({"userId", "email", "gender", "password", "userType", "course"})
	private User user;

}
