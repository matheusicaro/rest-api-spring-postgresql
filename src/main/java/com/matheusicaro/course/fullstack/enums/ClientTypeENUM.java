package com.matheusicaro.course.fullstack.enums;

public enum ClientTypeENUM {

	PESSOAFISICA(1, "Pessoa Física"), 
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int code;
	private String description;

	private ClientTypeENUM(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ClientTypeENUM toEnum(Integer id) {
		
		if(id == null)
			return null;
		
		
		for (ClientTypeENUM enumTypeCode : ClientTypeENUM.values()) {
			
			if(id.equals(enumTypeCode.getCode())) {
				return enumTypeCode;
			}
			
		}

		throw new IllegalArgumentException("Id inválido: " + id);
	}
}