package com.eudemy.controller.user;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.eudemy.models.JwtRequest;
import com.eudemy.models.JwtResponse;
import com.eudemy.models.Token;
import com.eudemy.models.User;
import com.eudemy.repositories.UserRepository;
import com.eudemy.security.JwtHelper;
import com.eudemy.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserService userService;
	@Autowired JwtHelper jwtHelper;
	@Autowired Token t;
	
//	sign up
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody User user){
		return userService.addUser(user);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<?> getCart(){
		return userService.getCart(t.getUserId());
	}
	
//	logIn with token
	@GetMapping("/login/token")
	public ResponseEntity<?> loginWithToken(){
		System.out.println(t.getUserId());
		JwtResponse response = new JwtResponse();
		response.setJwtToken(null);
		response.setAdmin(jwtHelper.isAdmin);
		User user=userRepository.findById(t.getUserId()).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with token");
		});
		response.setUsername(user.getFirstName()+" "+user.getLastName());
//		response.setLog(1);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
//	login
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired UserRepository userRepository;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@org.springframework.web.bind.annotation.RequestBody JwtRequest request) {
//    	System.out.println(request);
//    	System.out.println(request.getUsername());
    	
    	try {
    		this.doAuthenticate(request.getUsername(), request.getPassword());
    	}catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    	}
        


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        this.jwtHelper.roles=userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String token = this.helper.generateToken(userDetails);

        JwtResponse jr = new JwtResponse();
        jr.setJwtToken(token);
        User user=getUserIdByUsername(userDetails.getUsername());
        jr.setUsername(user.getFirstName()+" "+user.getLastName());
        jwtHelper.isAdminExist();
        jr.setAdmin(jwtHelper.isAdmin);
//        jr.setAdmin(jwtHelper.isAdmin);)
        return new ResponseEntity<>(jr, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        System.out.println(authentication);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

    }
    
    private User getUserIdByUsername(String username) {
    	User user=userRepository.findByEmail(username).orElse(null);
    	return user;
    }
    

//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        return "Credentials Invalid !!";
//    }

}
