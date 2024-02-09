package com.eudemy.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserType {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userTypeId;
	
	@Column
	@Min(5)
	@Max(50)
	private String userType;

}
