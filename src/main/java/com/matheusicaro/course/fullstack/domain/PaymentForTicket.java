package com.matheusicaro.course.fullstack.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.matheusicaro.course.fullstack.enums.PaymentOptionENUM;

@Entity
@JsonTypeName("PaymentForTicket")
@Table(name="_PAYMENT_FOR_TICKET")
public class PaymentForTicket extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date expiredDate;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date paymentDate;
	
	public PaymentForTicket() {

	}
	public PaymentForTicket(Integer id, Date paymentDate, Order order, PaymentOptionENUM paymentOption, Date expiredDate) {
		super(id, paymentOption, order);
		this.expiredDate = expiredDate;
		this.paymentDate = paymentDate;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	

}
