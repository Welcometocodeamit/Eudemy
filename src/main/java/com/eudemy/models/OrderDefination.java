package com.eudemy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDefination {
	
	private int totalPrice;
	
	private int [] courseId;

}
