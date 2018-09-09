package com.matheusicaro.course.fullstack.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.matheusicaro.course.fullstack.domain.Client;
import com.matheusicaro.course.fullstack.dto.ClientNewDTO;
import com.matheusicaro.course.fullstack.enums.ClientTypeENUM;
import com.matheusicaro.course.fullstack.repositories.ClientRepository;
import com.matheusicaro.course.fullstack.resources.exceptions.FieldMessage;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {

	}

	@Override
	public boolean isValid(ClientNewDTO clientNewDTO, ConstraintValidatorContext context) {

		// LISTA DE ERROS CRIADAS ANTERIORMENTE, ERRO PERSONALIZADO NO FORMATO JSON
		List<FieldMessage> list = new ArrayList<>();  
		
		
		if (clientNewDTO.getType().equals(ClientTypeENUM.PESSOAFISICA.getCode()) && !BR.isValidCPF(clientNewDTO.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj", "CPF é inválido"));
		}
 		
		if (clientNewDTO.getType().equals(ClientTypeENUM.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(clientNewDTO.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj", "CNPJ inválido"));
		}
 		
		Client client = clientRepository.findByEmail(clientNewDTO.getEmail());
		
		if( client != null)
			list.add(new FieldMessage("email", "Este e-mail já existe"));
		
 		for (FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
