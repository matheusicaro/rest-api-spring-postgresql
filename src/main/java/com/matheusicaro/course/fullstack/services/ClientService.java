package com.matheusicaro.course.fullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.Client;
import com.matheusicaro.course.fullstack.repositories.ClientRepository;
import com.matheusicaro.course.fullstack.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;
	
	public Client findClientById (Integer id) {
		
		Optional<Client> client = repository.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Não encontrado! " + "Id: " + id + ", Tipo: " + Client.class.getSimpleName()));
	}
	
}
