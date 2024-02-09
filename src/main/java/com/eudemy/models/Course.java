package com.eudemy.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	private String courseName;
	
	private String courseDescription;
	
	private String courseLevel;
	
//	one to many
//	student
	@OneToMany
	@JoinColumn(name = "courseUsers")
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
