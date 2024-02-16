package com.eudemy.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.models.OrderDefination;
import com.eudemy.models.Token;
import com.eudemy.service.user.UserOrderService;


@RestController
@RequestMapping("user")
public class OrderUserController {
	
//	create order
	@Autowired UserOrderService userOrderService;
	@Autowired Token t;
	
	@PostMapping("/order")
	public ResponseEntity<?> createOrder( @RequestBody OrderDefination orderDefination){
		return userOrderService.createOrder(t.getUserId(), orderDefination);
	}
	
	@GetMapping("/order")
	public ResponseEntity<?> getOrders(){
		return userOrderService.getOrderByUser(t.getUserId());
	}
}
