package com.eudemy.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.models.FilterDate;
import com.eudemy.service.admin.AdminOrderService;

@RestController
@RequestMapping("admin")
public class AdminOrderController {
	
	@Autowired AdminOrderService adminOrderService;
	
//	get all orders
	@GetMapping("/order/pageNo={pageNo}")
	public ResponseEntity<?> getAllOrders(@PathVariable int pageNo){
		return adminOrderService.getAllOrders(pageNo);
	}
	
//	get orders for chart
	@GetMapping("/orders/chart")
	public ResponseEntity<?> getDataForChart() throws ParseException{
		return adminOrderService.getAllOrdersForChart();
	}
	
//	get weekly orders for chart
	@GetMapping("/orders/weekly")
	public ResponseEntity<?> getWeeklyData() throws ParseException{
		return adminOrderService.getWeeklyOrders();
	}
	
//	get daily orders by dates
	@PostMapping("/orders/daily/sort")
	public ResponseEntity<?> getDailyOrdersBetween(@RequestBody FilterDate date) throws ParseException{
		return adminOrderService.getAllOrdersForDailyChart2(date.getFromDate(), date.getToDate());
	}
	
//	get weekly order by dates
	@PostMapping("/orders/weekly/sort")
	public ResponseEntity<?> getWeeklyOrdersSortByDate(@RequestBody FilterDate date) throws ParseException{
		return adminOrderService.getWeeklyOrdersSortByDate(date.getFromDate(), date.getToDate());
	}

}
