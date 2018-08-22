package com.matheusicaro.course.fullstack.domain;

import javax.persistence.Entity;

import com.matheusicaro.course.fullstack.enums.PaymentOptionENUM;


@Entity
public class PaymentForCart extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer numberOfPloats;

	public PaymentForCart() {
	
	}

	public PaymentForCart(Integer id, PaymentOptionENUM paymentOption, Order order, Integer numberOfPloats) {
		super(id, paymentOption, order);
		this.numberOfPloats = numberOfPloats;
	}

	public Integer getNumberOfPloats() {
		return numberOfPloats;
	}

	public void setNumberOfPloats(Integer numberOfPloats) {
		this.numberOfPloats = numberOfPloats;
	}
	
	
}
