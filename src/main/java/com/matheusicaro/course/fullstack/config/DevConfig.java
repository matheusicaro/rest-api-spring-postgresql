package com.matheusicaro.course.fullstack.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matheusicaro.course.fullstack.services.EmailService;
import com.matheusicaro.course.fullstack.services.EmailSmtpService;
import com.matheusicaro.course.fullstack.services.InstanceToDataBaseService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private InstanceToDataBaseService dataBaseService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dataBaseService.instantiateDevDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new EmailSmtpService();
	}
}