package com.matheusicaro.course.fullstack.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheusicaro.course.fullstack.enums.PaymentOptionENUM;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="_PAYMENT")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer paymentOption;
		
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "order_id")
	@MapsId   		
	private Order order;

	public Payment() {
		
	}
	
	public Payment(Integer id, PaymentOptionENUM paymentOption, Order order) {
		super();
		this.id = id;
		this.paymentOption = (paymentOption == null) ? null : paymentOption.getCode();
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentOptionENUM getPaymentOption() {
		return PaymentOptionENUM.toEnum(paymentOption);
	}

	public void setPaymentOption(Integer paymentOption) {
		this.paymentOption = paymentOption;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
