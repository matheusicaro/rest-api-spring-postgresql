package com.matheusicaro.course.fullstack.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String fieldName;

	
	public FieldMessage() {
	}
 	
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	
	public String getFieldName() {
		return fieldName;
	}
 	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
 	public String getMessage() {
		return message;
	}
 	public void setMessage(String message) {
		this.message = message;
	}
}
