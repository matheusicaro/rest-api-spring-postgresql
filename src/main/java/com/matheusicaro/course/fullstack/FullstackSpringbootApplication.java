package com.matheusicaro.course.fullstack;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.domain.Product;
import com.matheusicaro.course.fullstack.repositories.CategoryRespository;
import com.matheusicaro.course.fullstack.repositories.ProductRepository;

@SpringBootApplication
public class FullstackSpringbootApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRespository categoryRespository;
	@Autowired
	private ProductRepository productRepository;

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

	}
}
