package com.matheusicaro.course.fullstack.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.matheusicaro.course.fullstack.domain.Client;
import com.matheusicaro.course.fullstack.dto.ClientNewDTO;
import com.matheusicaro.course.fullstack.repositories.ClientRepository;
import com.matheusicaro.course.fullstack.resources.exceptions.FieldMessage;
import com.sun.javafx.collections.MappingChange.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	HttpServletRequest request; 
	
	@Override
	public void initialize(ClientInsert ann) {

	}

	@Override
	public boolean isValid(ClientNewDTO clientNewDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();  
		
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		Integer uriId = Integer.parseInt(map.map("id"));
		
		Client client = clientRepository.findByEmail(clientNewDTO.getEmail());
		
		if( client != null && client.getId().equals(uriId))
			list.add(new FieldMessage("email", "Este e-mail já existe"));
		
 		for (FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
