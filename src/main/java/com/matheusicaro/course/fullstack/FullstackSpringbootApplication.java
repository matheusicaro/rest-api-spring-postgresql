package com.matheusicaro.course.fullstack;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.domain.City;
import com.matheusicaro.course.fullstack.domain.Product;
import com.matheusicaro.course.fullstack.domain.State;
import com.matheusicaro.course.fullstack.repositories.CategoryRespository;
import com.matheusicaro.course.fullstack.repositories.CityRepository;
import com.matheusicaro.course.fullstack.repositories.ProductRepository;
import com.matheusicaro.course.fullstack.repositories.StateRepository;

@SpringBootApplication
public class FullstackSpringbootApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRespository categoryRespository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FullstackSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category category_1 = new Category(null, "informatic");
		Category category_2 = new Category(null, "office");
		
		Product product_1 = new Product(null, "computer", 2000.00);
		Product product_2 = new Product(null, "printer", 800.00);
		Product product_3 = new Product(null, "mouse", 80.00);
		
		category_1.getProducts().addAll(Arrays.asList(product_1, product_2, product_3));
		category_1.getProducts().addAll(Arrays.asList(product_2));
				
		product_1.getCategories().addAll(Arrays.asList(category_1));
		product_2.getCategories().addAll(Arrays.asList(category_1, category_2));
		product_3.getCategories().addAll(Arrays.asList(category_1));
		
		categoryRespository.saveAll(Arrays.asList(category_1, category_2));
		productRepository.saveAll(Arrays.asList(product_1, product_2, product_3));
		
		State state_1 = new State(null, "Minas Gerais");
		State state_2 = new State(null, "São Paulo");
		
		City city_1 = new City(null, "Uberlândia", state_1);
		City city_2 = new City(null, "São Paulo", state_2);
		City city_3 = new City(null, "Campinas", state_2);
		
		state_1.getCities().addAll(Arrays.asList(city_1));
		state_2.getCities().addAll(Arrays.asList(city_2, city_3));
		
		stateRepository.saveAll(Arrays.asList(state_1, state_2));
		cityRepository.saveAll(Arrays.asList(city_1, city_2, city_3));
		

	}
}