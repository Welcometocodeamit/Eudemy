package com.eudemy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
