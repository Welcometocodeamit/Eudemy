package com.eudemy.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String gender;
	
	@ManyToOne
	@JoinColumn(name = "userType")
	private UserType userType;
	
//	@JsonIgnoreProperties({"carts", "products"})
	@ManyToMany
	@JoinTable(name = "user_course_table", 
	joinColumns = {
		@JoinColumn(name="userId", referencedColumnName = "userId")
		
	},
	inverseJoinColumns = {
			@JoinColumn(name="courseId", referencedColumnName = "courseId")
	}
	)
	private List<Course> course;
	
	
	
	@JsonIgnore
	private boolean enabled;
	
	@JsonIgnore
	private Collection<? extends GrantedAuthority> authorities;
	
	@JsonIgnore
	private boolean credentialsNonExpired;
	
	@JsonIgnore
	private boolean accountNonExpired;
	
	@JsonIgnore
	private boolean accountNonLocked;
	
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return this.authorities;
//	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        
//        list.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authorities;
    }
	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	

}
