package com.matheusicaro.course.fullstack.services;

import org.springframework.mail.SimpleMailMessage;

import com.matheusicaro.course.fullstack.domain.Order;

public interface EmailService {
 	
	void sendOrderConfirmationEmail(Order order);
	
	void sendEmail(SimpleMailMessage message);
}