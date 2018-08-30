package com.matheusicaro.course.fullstack.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(int value, String message, Long timestamp) {
		super(value, message, timestamp);

	}

	public List<FieldMessage> getErros() {
		return errors;
	}

	public void addError(String fieldName, String messagem) {
		
		errors.add(new FieldMessage(fieldName, messagem));
	}
	
}
