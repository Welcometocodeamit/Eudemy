package com.eudemy.service.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.DTO.ChartOrderDTO;
import com.eudemy.DTO.ChartWeeklyOrder;
import com.eudemy.models.Orders;
import com.eudemy.repositories.OrderRepository;

@Service
public class AdminOrderService {
	
	@Autowired OrderRepository orderRepository;
	@Autowired ChartOrderDTO chartOrderDTO;
	
//	get all orders
	public ResponseEntity<?> getAllOrders(int page){
		Pageable p = PageRequest.of(page, 6);
		Page<Orders> orders = orderRepository.findAll(p);
		if(orders.getSize() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "orders not found");
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
//	get all daily orders for chart
	public ResponseEntity<?> getAllOrdersForChart() throws ParseException{
		List<Orders> orders=orderRepository.findAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Integer> chartOrders = new HashMap<String, Integer>();
		orders.forEach((order)->{
			 String date = sdf.format(order.getOrderDate());
			chartOrders.put(date, chartOrders.getOrDefault(date,0)+1);
		});
		List<ChartOrderDTO> toSend=new ArrayList<>();
		 for (Map.Entry<String, Integer> entry : chartOrders.entrySet()) {
			 Date date=new SimpleDateFormat("yyyy-MM-dd").parse(entry.getKey());  
			 	ChartOrderDTO cdto=new ChartOrderDTO();
	            Integer count = entry.getValue();
	            cdto.setDate(date);
	            cdto.setCount(count);
	            toSend.add(cdto);
	        }

		return new ResponseEntity<>(toSend, HttpStatus.OK);
	}
	
//	get all orders by filter
	public ResponseEntity<?> getAllOrdersForDailyChart2(Date fromDate, Date toDate)throws ParseException{
		
		List<Orders> orders=orderRepository.findByOrderDateBetween(fromDate, toDate );
		System.out.println(orders);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Integer> chartOrders = new HashMap<String, Integer>();
		orders.forEach((order)->{ 
			 String date = sdf.format(order.getOrderDate());
			chartOrders.put(date, chartOrders.getOrDefault(date,0)+1);
		});
//		
		List<ChartOrderDTO> toSend=new ArrayList<>();
		 for (Map.Entry<String, Integer> entry : chartOrders.entrySet()) {
			 Date date=new SimpleDateFormat("yyyy-MM-dd").parse(entry.getKey());  
			 	ChartOrderDTO cdto=new ChartOrderDTO();
	            Integer count = entry.getValue();
	            cdto.setDate(date);
	            cdto.setCount(count);
	            toSend.add(cdto);
	        }
		return new ResponseEntity<>(toSend, HttpStatus.OK);
	}
	
//	get all weekly orders for chart
	public ResponseEntity<?> getWeeklyOrders() throws ParseException{
		
		List<Orders> orders = orderRepository.findAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Integer> chartOrders = new HashMap<>();

		// Group orders by week
		Map<Object, List<Orders>> ordersByWeek = orders.stream()
		    .collect(Collectors.groupingBy(order -> {
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(order.getOrderDate());
		        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		        return sdf.format(cal.getTime());
		    }));

		// Count orders for each week
		ordersByWeek.forEach((week, orderList) -> {
		    chartOrders.put((String) week, orderList.size());
		});

		List<ChartWeeklyOrder> toSend = new ArrayList<>();
		chartOrders.forEach((week, count) -> {
			ChartWeeklyOrder wo= new ChartWeeklyOrder();
			wo.setDate(week);
			wo.setCount(count);
			toSend.add(wo);	    
		});
		return new ResponseEntity<>(toSend, HttpStatus.OK);
	}
	
//	get weekly orders for chart sort by date
	public ResponseEntity<?> getWeeklyOrdersSortByDate(Date fromDate, Date toDate) throws ParseException{
		
		List<Orders> orders = orderRepository.findByOrderDateBetween(fromDate, toDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Integer> chartOrders = new HashMap<>();

		// Group orders by week
		Map<Object, List<Orders>> ordersByWeek = orders.stream()
		    .collect(Collectors.groupingBy(order -> {
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(order.getOrderDate());
		        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		        return sdf.format(cal.getTime());
		    }));

		// Count orders for each week
		ordersByWeek.forEach((week, orderList) -> {
		    chartOrders.put((String) week, orderList.size());
		});

		List<ChartWeeklyOrder> toSend = new ArrayList<>();
		chartOrders.forEach((week, count) -> {
			ChartWeeklyOrder wo= new ChartWeeklyOrder();
			wo.setDate(week);
			wo.setCount(count);
			toSend.add(wo);	    
		});
		return new ResponseEntity<>(toSend, HttpStatus.OK);
	}

}
