package com.eudemy.service.user;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.config.AppConfig;
import com.eudemy.models.ResponseWrapper;
import com.eudemy.models.User;
import com.eudemy.repositories.UserRepository;

import io.jsonwebtoken.lang.Collections;

@Service
public class UserService {
	
	@Autowired UserRepository userRepository;
	@Autowired ResponseWrapper wrap;
	@Autowired User user;
	@Autowired AppConfig appConfig;
	
//	sign up
	public ResponseEntity<?> addUser(User user){
		user.setPassword(appConfig.passwordEncoder().encode(user.getPassword()));
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(user.getUserType().getUserTypeId()==1) {
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		user.setAuthorities(list);
		 User addedUser= userRepository.save(user);
		 if(addedUser == null) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sign up failed");
		 }
		 wrap.setMessage("Sign up sucess");
		 return new ResponseEntity<>(wrap, HttpStatus.OK);
	}
	
//	get cart
	public ResponseEntity<?> getCart(int userId){
		User user=userRepository.findById(userId).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		});
		return new ResponseEntity<>(user.getCourse(), HttpStatus.OK);
	}

}
