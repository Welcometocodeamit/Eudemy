package com.eudemy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.service.admin.AdminUserService;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
	
	@Autowired AdminUserService adminUserService;
	
//	get all users
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){
		return adminUserService.getAllUsers();
	}
	
//	delete user by id
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable int userId){
		return adminUserService.deleteUserById(userId);
	}
	

}
