package com.eudemy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartWeeklyOrder {
	
	private String date;
	
	private int count;

}
