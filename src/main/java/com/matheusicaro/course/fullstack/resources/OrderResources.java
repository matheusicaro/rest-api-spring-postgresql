package com.matheusicaro.course.fullstack.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusicaro.course.fullstack.domain.Order;
import com.matheusicaro.course.fullstack.services.OrderService;

@RestController()
@RequestMapping(path="/orders")
public class OrderResources {
	
	@Autowired
	OrderService service;
	
	@RequestMapping(value="{Id}", method=RequestMethod.GET)
	public ResponseEntity<?> findById (@PathVariable Integer Id) {
		
		Order order = service.findById(Id);
		return ResponseEntity.ok().body(order);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Order order) {
		order = service.insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
