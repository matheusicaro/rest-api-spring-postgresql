package com.matheusicaro.course.fullstack;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheusicaro.course.fullstack.domain.AddressHouse;
import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.domain.City;
import com.matheusicaro.course.fullstack.domain.Client;
import com.matheusicaro.course.fullstack.domain.Order;
import com.matheusicaro.course.fullstack.domain.OrderItem;
import com.matheusicaro.course.fullstack.domain.Payment;
import com.matheusicaro.course.fullstack.domain.PaymentForCart;
import com.matheusicaro.course.fullstack.domain.PaymentForTicket;
import com.matheusicaro.course.fullstack.domain.Product;
import com.matheusicaro.course.fullstack.domain.State;
import com.matheusicaro.course.fullstack.enums.ClientTypeENUM;
import com.matheusicaro.course.fullstack.enums.PaymentOptionENUM;
import com.matheusicaro.course.fullstack.repositories.AddressHouseRepository;
import com.matheusicaro.course.fullstack.repositories.CategoryRespository;
import com.matheusicaro.course.fullstack.repositories.CityRepository;
import com.matheusicaro.course.fullstack.repositories.ClientRepository;
import com.matheusicaro.course.fullstack.repositories.OrderItemRespository;
import com.matheusicaro.course.fullstack.repositories.OrderRespository;
import com.matheusicaro.course.fullstack.repositories.PaymentRespository;
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
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressHouseRepository addressRepository;
	@Autowired
	private PaymentRespository paymentRespository;
	@Autowired
	private OrderRespository orderRespository;
	@Autowired
	private OrderItemRespository orderItemRespository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(FullstackSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category category_1 = new Category(null, "informatic");
		Category category_2 = new Category(null, "office");
		Category category_3 = new Category(null, "Cama mesa e banho");
		Category category_4 = new Category(null, "Eletrônicos");
		Category category_5 = new Category(null, "Jardinagem");
		Category category_6 = new Category(null, "Decoração");
		Category category_7 = new Category(null, "Perfumaria");
		
		Product product_1 = new Product(null, "computer", 2000.00);
		Product product_2 = new Product(null, "printer", 800.00);
		Product product_3 = new Product(null, "mouse", 80.00);
		Product product_4 = new Product(null, "Mesa de escritório", 300.00);
		Product product_5 = new Product(null, "Toalha", 50.00);
		Product product_6 = new Product(null, "Colcha", 200.00);
		Product product_7 = new Product(null, "TV true color", 1200.00);
		Product product_8 = new Product(null, "Roçadeira", 800.00);
		Product product_9 = new Product(null, "Abajour", 100.00);
		Product product_10 = new Product(null, "Pendente", 180.00);
		Product product_11 = new Product(null, "Shampoo", 90.00);
		
		category_1.getProducts().addAll(Arrays.asList(product_1, product_2, product_3));
		category_1.getProducts().addAll(Arrays.asList(product_2));
		category_2.getProducts().addAll(Arrays.asList(product_2, product_4));
		category_3.getProducts().addAll(Arrays.asList(product_5, product_6));
		category_4.getProducts().addAll(Arrays.asList(product_1, product_2, product_3, product_7));
		category_5.getProducts().addAll(Arrays.asList(product_8));
		category_6.getProducts().addAll(Arrays.asList(product_9, product_10));
		category_7.getProducts().addAll(Arrays.asList(product_11));		
		
		product_1.getCategories().addAll(Arrays.asList(category_1));
		product_2.getCategories().addAll(Arrays.asList(category_1, category_2));
		product_3.getCategories().addAll(Arrays.asList(category_1));
		product_4.getCategories().addAll(Arrays.asList(category_2));
		product_5.getCategories().addAll(Arrays.asList(category_3));
		product_6.getCategories().addAll(Arrays.asList(category_3));
		product_7.getCategories().addAll(Arrays.asList(category_4));
		product_8.getCategories().addAll(Arrays.asList(category_5));
		product_9.getCategories().addAll(Arrays.asList(category_6));
		product_10.getCategories().addAll(Arrays.asList(category_6));
		product_11.getCategories().addAll(Arrays.asList(category_7));	
		
		categoryRespository.saveAll(Arrays.asList(category_1, category_2, category_3, category_4, category_5, category_6, category_7));
		productRepository.saveAll(Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10, product_11));
		
		State state_1 = new State(null, "Minas Gerais");
		State state_2 = new State(null, "São Paulo");
		
		City city_1 = new City(null, "Uberlândia", state_1);
		City city_2 = new City(null, "São Paulo", state_2);
		City city_3 = new City(null, "Campinas", state_2);
		
		state_1.getCities().addAll(Arrays.asList(city_1));
		state_2.getCities().addAll(Arrays.asList(city_2, city_3));
		
		stateRepository.saveAll(Arrays.asList(state_1, state_2));
		cityRepository.saveAll(Arrays.asList(city_1, city_2, city_3));
		
		Client client_1 = new Client(null, "Maria Silva", "maria@gmail.com", "12345678910", ClientTypeENUM.PESSOAFISICA);
		
		client_1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		AddressHouse address_1 = new AddressHouse(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client_1, city_1);
		AddressHouse address_2 = new AddressHouse(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client_1, city_2);
		
		client_1.getHouseAndress().addAll(Arrays.asList(address_1, address_2));
		
		clientRepository.saveAll(Arrays.asList(client_1));
		addressRepository.saveAll(Arrays.asList(address_1, address_2));		
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:MM");

		Order order_1 = new Order (null, dateFormatter.parse("23/08/2018 12:00"), client_1, address_1);
		Order order_2 = new Order (null, dateFormatter.parse("23/08/2018 00:00"), client_1, address_2);
		
		Payment payment_1 = new PaymentForCart(null, order_1, PaymentOptionENUM.APPROVED, 6);
		Payment payment_2 = new PaymentForTicket(null, dateFormatter.parse("25/08/2018 00:00"), order_2, PaymentOptionENUM.PENDING, null);
		
		order_1.setPayment(payment_1);
		order_2.setPayment(payment_2);
		
		client_1.getOrders().addAll(Arrays.asList(order_1, order_2));
		
		orderRespository.saveAll(Arrays.asList(order_1, order_2));
		paymentRespository.saveAll(Arrays.asList(payment_1, payment_2));
		
		OrderItem orderItem_1 = new OrderItem(order_1, product_1, 0.00, 1, 2000.00);
		OrderItem orderItem_2 = new OrderItem(order_1, product_3, 0.00, 2, 80.00);
		OrderItem orderItem_3 = new OrderItem(order_2, product_2, 100.00, 1, 800.00);

		order_1.getOrderItems().addAll(Arrays.asList(orderItem_1, orderItem_2));
		order_2.getOrderItems().addAll(Arrays.asList(orderItem_3));
		
		product_1.getOrderItems().addAll(Arrays.asList(orderItem_1));
		product_2.getOrderItems().addAll(Arrays.asList(orderItem_3));
		product_3.getOrderItems().addAll(Arrays.asList(orderItem_2));
		
		orderItemRespository.saveAll(Arrays.asList(orderItem_1, orderItem_2, orderItem_3));
		
		System.out.println("\n\n*** SUCCESS ***");

	}
}
