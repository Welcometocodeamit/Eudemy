package com.eudemy.models;

import java.util.List;

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
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	private String courseName;
	
	private String courseDescription;
	
	private String courseLevel;
	
	private String image;
	
	private boolean fe;
	
	private double price;
	
//	one to many
//	student
	@ManyToMany(mappedBy = "course")
	@JsonIgnore
	private List<User> user;
	
//	one to one
//	instructor
//	@OneToOne
//	@JoinColumn(name = "courseInstructor")
//	private User courseInstructor;
	
//	many to one category
	@ManyToOne
	@JoinColumn(name = "courseCategory")
	@JsonIgnoreProperties({"course"})
	private Category category;
	

}
