package com.matheusicaro.course.fullstack.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailSmtpService extends EmailAbstractService {

	@Autowired
	private MailSender mailSender;

	private static final Logger LOG = LoggerFactory.getLogger(EmailSmtpService.class);

	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Enviando email...");
		mailSender.send(message);
		LOG.info("Email enviado");
	}

}
