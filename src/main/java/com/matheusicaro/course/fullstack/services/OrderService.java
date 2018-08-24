package com.matheusicaro.course.fullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.Order;
import com.matheusicaro.course.fullstack.repositories.OrderRespository;
import com.matheusicaro.course.fullstack.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	OrderRespository orderRespository;
	
	public Order findById(Integer id) {
		
		Optional<Order> order = orderRespository.findById(id);
		
		return order.orElseThrow(() -> new ObjectNotFoundException(
				"Não encontrado! " + "Id: " + id + ", Tipo: " + Order.class.getSimpleName()));		
	}
}
