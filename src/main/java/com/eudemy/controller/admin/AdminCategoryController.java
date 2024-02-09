package com.eudemy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eudemy.models.Category;
import com.eudemy.service.admin.AdminCategoryService;
import com.eudemy.service.user.UserCategoryService;

@RequestMapping("/admin/category")
@RestController
public class AdminCategoryController {
	
	@Autowired 
	private AdminCategoryService adminCategoryService;
	
	@Autowired
	private UserCategoryService userCategoryService;
	
	
//	add category
	@PostMapping("")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		return adminCategoryService.addCategory(category);
	}
	
//	update category
	@PutMapping("")
	public ResponseEntity<?> updateCategory(@RequestBody Category category){
		return adminCategoryService.updateCategory(category);
	}
	
//	get all categories
	@GetMapping("")
	public ResponseEntity<?> getAllCategories(){
		return userCategoryService.getAllCategories();
	}
	
//	delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteById(@PathVariable int categoryId){
		return adminCategoryService.deleteById(categoryId);
	}
}
