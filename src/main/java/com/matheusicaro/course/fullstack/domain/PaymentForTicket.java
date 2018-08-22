package com.matheusicaro.course.fullstack.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.matheusicaro.course.fullstack.enums.PaymentOptionENUM;

@Entity
public class PaymentForTicket extends Payment{
	private static final long serialVersionUID = 1L;

	private Date expiredDate;
	private Date paymentDate;
	
	public PaymentForTicket() {

	}
	public PaymentForTicket(Integer id, PaymentOptionENUM paymentOption, Order order, Date expiredDate, Date paymentDate) {
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
