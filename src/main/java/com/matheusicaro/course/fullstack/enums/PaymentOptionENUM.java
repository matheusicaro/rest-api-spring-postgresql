package com.matheusicaro.course.fullstack.enums;

public enum PaymentOptionENUM {

	PENDING(1, "Pending"), 
	APPROVED(1, "Approved"), 
	CANCELED(1, "Canceled");
	
	private int code;
	private String description;

	private PaymentOptionENUM(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static PaymentOptionENUM toEnum(Integer code) {
		
		if(code == null)
			return null;
		
		
		for (PaymentOptionENUM enumTypeCode : PaymentOptionENUM.values()) {
			
			if(code.equals(enumTypeCode.getCode())) {
				return enumTypeCode;
			}
			
		}

		throw new IllegalArgumentException("Codigo inválido: " + code);
	}

}
