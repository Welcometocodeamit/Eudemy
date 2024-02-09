package com.eudemy.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.service.user.UserCategoryService;

@RestController
@RequestMapping("/user")
public class UserCategoryController {
	
	@Autowired UserCategoryService userCategoryService;
	
//	get category by name
	@GetMapping("/categoryname")
	public ResponseEntity<?> getCategoryByName(@RequestParam(name = "categoryname") String categoryName){
		return userCategoryService.getCateoryByName(categoryName);
	}
//	
//	get all categories
	@GetMapping("/category")
	public ResponseEntity<?> getCategories(){
		return userCategoryService.getAllCategories();
	}
	


}
