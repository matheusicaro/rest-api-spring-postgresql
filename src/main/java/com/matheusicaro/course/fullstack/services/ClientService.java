package com.matheusicaro.course.fullstack.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.AddressHouse;
import com.matheusicaro.course.fullstack.domain.City;
import com.matheusicaro.course.fullstack.domain.Client;
import com.matheusicaro.course.fullstack.dto.ClientDTO;
import com.matheusicaro.course.fullstack.dto.ClientNewDTO;
import com.matheusicaro.course.fullstack.enums.ClientTypeENUM;
import com.matheusicaro.course.fullstack.repositories.AddressHouseRepository;
import com.matheusicaro.course.fullstack.repositories.ClientRepository;
import com.matheusicaro.course.fullstack.services.exceptions.DataIntegrityException;
import com.matheusicaro.course.fullstack.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;
	
	@Autowired
	AddressHouseRepository addressHouseRepository;
	public void delete(Integer id) {
		
		findById(id);
		
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete because request are opens");
		}
	}
	
	public List<Client> findAll () {
		return repository.findAll();
	}
	
	public Client findById (Integer id) {
		
		Optional<Client> client = repository.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Não encontrado! " + "Id: " + id + ", Tipo: " + Client.class.getSimpleName()));
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
		
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO newClientDTO) {
		
		Client newClient = new Client(null, newClientDTO.getName(), newClientDTO.getEmail(), newClientDTO.getCpf_cnpj(), ClientTypeENUM.toEnum(newClientDTO.getType()))	;
		City city = new City(newClientDTO.getCityId(), null, null);
		AddressHouse address = new AddressHouse(null, newClientDTO.getStreet(), newClientDTO.getStreetNumber(), newClientDTO.getComplement(), newClientDTO.getDistrict(),
				newClientDTO.getCep(), newClient, city);
		
		newClient.getHouseAndress().add(address);
		newClient.getPhones().add(newClientDTO.getPhone_1());
		
		if(newClientDTO.getPhone_2() != null)
			newClient.getPhones().add(newClientDTO.getPhone_2());
		if(newClientDTO.getPhone_3() != null)
			newClient.getPhones().add(newClientDTO.getPhone_3());
		
		return newClient;
	}
	
	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		client = repository.save(client);
		addressHouseRepository.saveAll(client.getHouseAndress());
		return client;
	}
	
	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
	}

	public Client update(Client client) {
		
		Client newClient = findById(client.getId());
		updateData(newClient, client);
		return repository.save(newClient);
	}

	
}
