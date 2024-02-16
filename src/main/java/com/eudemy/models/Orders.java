package com.eudemy.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double totlePrice;
	
	private Date orderDate;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({"email", "password", "gender", "userType", "course"})
	private User user;
	
	@ManyToMany
	@JoinTable(name = "order_course_table", 
	joinColumns = {
		@JoinColumn(name="orderId", referencedColumnName = "id")
		
	},
	inverseJoinColumns = {
			@JoinColumn(name="courseId", referencedColumnName = "courseId")
	})
	@JsonIgnoreProperties({"category"})
	private List<Course> course;

}
