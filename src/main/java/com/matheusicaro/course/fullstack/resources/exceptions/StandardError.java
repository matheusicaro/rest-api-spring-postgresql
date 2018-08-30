package com.matheusicaro.course.fullstack.resources.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private int value;
	private String message;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Long timestamp;

	public StandardError(int value, String message, Long timestamp) {
		super();
		this.value = value;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
