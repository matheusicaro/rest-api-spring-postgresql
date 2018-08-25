package com.matheusicaro.course.fullstack.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matheusicaro.course.fullstack.domain.Client;
import com.matheusicaro.course.fullstack.services.ClientService;

@RestController()
@RequestMapping(path="/clients")
public class ClientResource {

	@Autowired
	ClientService service;
	
	
	@RequestMapping(value="{Id}", method=RequestMethod.GET)
	public ResponseEntity<?> findClientById (@PathVariable Integer Id) {
		
		Client client = service.findClientById(Id);
		return ResponseEntity.ok().body(client);
		
	}
	
	@RequestMapping
	public void insertClient() {
		
	}
}
