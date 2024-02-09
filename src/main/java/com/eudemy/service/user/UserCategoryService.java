package com.eudemy.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.Category;
import com.eudemy.repositories.CategoryRepository;

@Service
public class UserCategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
//	get all categories
	public ResponseEntity<?> getAllCategories(){
		List<Category> categories=categoryRepository.findAll();
		
		if(categories.size() == 0) {
			return new ResponseEntity<>("Categories not found", HttpStatus.OK);
		}
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
//	get category by name
	public ResponseEntity<?> getCateoryByName(String categoryName){
		System.out.println(categoryName);
		Category foundCategory=categoryRepository.findByCategoryTitle(categoryName).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, categoryName+" category not found");
		});
		return new ResponseEntity<>(foundCategory, HttpStatus.OK);
	}

}
