package com.eudemy.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eudemy.security.JwtAuthEntryPoint;
import com.eudemy.security.JwtAuthFilter;


@Configuration
public class SecurityConfig {
	
	@Autowired
    private JwtAuthEntryPoint point;
	
    @Autowired
    private JwtAuthFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf->csrf.disable());
        http.cors(cors->cors.disable());
        
        
        http.authorizeHttpRequests(req->req
        		.anyRequest().permitAll()
//        		.requestMatchers(HttpMethod.OPTIONS, "**").permitAll()
//				.requestMatchers("/user/signup").permitAll()
//				.requestMatchers("/swagger-ui/index.html#/**").permitAll()
//				.requestMatchers("user/login").permitAll()
//				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//				.requestMatchers("/admin/**").hasRole("ADMIN")
//				.anyRequest().authenticated()
				);

        
        http.exceptionHandling(ex->ex.authenticationEntryPoint(point));
        
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

