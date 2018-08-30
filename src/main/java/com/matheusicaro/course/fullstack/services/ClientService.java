package com.matheusicaro.course.fullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.Client;
import com.matheusicaro.course.fullstack.dto.ClientDTO;
import com.matheusicaro.course.fullstack.repositories.ClientRepository;
import com.matheusicaro.course.fullstack.services.exceptions.DataIntegrityException;
import com.matheusicaro.course.fullstack.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;
	
	public Client findById (Integer id) {
		
		Optional<Client> client = repository.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Não encontrado! " + "Id: " + id + ", Tipo: " + Client.class.getSimpleName()));
	}
	
	public Client insert(Client client) {

		client.setId(null);
		return repository.save(client);
	}

	public Client update(Client client) {
		
		Client newClient = findById(client.getId());
		updateData(newClient, client);
		return repository.save(newClient);
	}

	public void delete(Integer id) {
		
		findById(id);
		
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete because Tabelas Relacionas");
		}
	}
	
	public List<Client> findAll () {
		return repository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
		
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
	}
	
	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
	}
	
}
