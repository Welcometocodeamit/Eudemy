package com.eudemy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eudemy.models.Orders;
import com.eudemy.models.User;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	List<Orders> findByUser(User user);

}
