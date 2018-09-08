package com.matheusicaro.course.fullstack.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matheusicaro.course.fullstack.services.InstanceToDataBaseService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Autowired
	private InstanceToDataBaseService dataBaseService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategyDataBase;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if(strategyDataBase.equals("none"))
			return false;
		
		dataBaseService.instantiateDevDatabase();
		return true;
	}
}