package com.matheusicaro.course.fullstack.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.matheusicaro.course.fullstack.enums.PaymentOptionENUM;


@Entity
@Table(name="_PAYMENT_FOR_CART")
public class PaymentForCart extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer numberOfPloats;

	public PaymentForCart() {
	
	}

	public PaymentForCart(Integer id, Order order, PaymentOptionENUM paymentOption, Integer numberOfPloats) {
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
