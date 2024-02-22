package com.eudemy.DTO;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartOrderDTO {
	
	private Date date;
	
	private int count;

}
