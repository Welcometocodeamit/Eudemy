package com.eudemy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails admin=User.builder().username("admin").password(passwordEncoder().encode("admin")).build();
//		
//		UserDetails manager=User.builder().username("manager").password(passwordEncoder().encode("manager")).build();
//		
//		UserDetails user=User.builder().username("user").password(passwordEncoder().encode("user")).build();
//		
//		return new InMemoryUserDetailsManager(admin, manager, user);
//	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
}
