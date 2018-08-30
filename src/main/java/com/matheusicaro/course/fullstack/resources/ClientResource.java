package com.matheusicaro.course.fullstack.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusicaro.course.fullstack.domain.Client;
import com.matheusicaro.course.fullstack.dto.ClientDTO;
import com.matheusicaro.course.fullstack.dto.NewClientDTO;
import com.matheusicaro.course.fullstack.services.ClientService;

@RestController()
@RequestMapping(path="/clients")
public class ClientResource {

	@Autowired
	ClientService service;
	
	@RequestMapping(value= "/{Id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer Id){
		
		service.delete(Id);
		return ResponseEntity.noContent().build();
	}	
	
	@RequestMapping(value="{Id}", method=RequestMethod.GET)
	public ResponseEntity<?> findById (@PathVariable Integer Id) {
		
		Client client = service.findById(Id);
		return ResponseEntity.ok().body(client);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll () {
		
		List<Client> list = service.findAll();
		List<ClientDTO> listDTO = list.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Client> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));  

		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert (@RequestBody NewClientDTO newClientDTO){
	
		Client newClient = service.fromDTO(newClientDTO);
		newClient = service.insert(newClient);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(newClient.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value= "/{Id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update (@Valid @RequestBody ClientDTO clientDTO, @PathVariable Integer Id){
		
		Client client = service.fromDTO(clientDTO);
		client.setId(Id);
		client = service.update(client);
		return ResponseEntity.noContent().build();
	}	
}
