package com.matheusicaro.course.fullstack.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.matheusicaro.course.fullstack.domain.Order;

public abstract class EmailAbstractService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Order order) {
		SimpleMailMessage mailMessage = prepareSimpleMailMessageFromOrder(order);
		sendEmail(mailMessage);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(order.getClient().getEmail());
		mailMessage.setFrom(sender);
		mailMessage.setSubject("Pedido confirmado! Código: " + order.getId());
		mailMessage.setSentDate(new Date(System.currentTimeMillis()));
		mailMessage.setText(order.toString());
		return mailMessage;
	}

}
