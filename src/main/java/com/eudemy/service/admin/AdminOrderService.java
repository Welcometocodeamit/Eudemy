package com.eudemy.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.Orders;
import com.eudemy.repositories.OrderRepository;

@Service
public class AdminOrderService {
	
	@Autowired OrderRepository orderRepository;
	
//	get all orders
	public ResponseEntity<?> getAllOrders(int page){
		Pageable p = PageRequest.of(page, 6);
		Page<Orders> orders = orderRepository.findAll(p);
		if(orders.getSize() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "orders not found");
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

}
