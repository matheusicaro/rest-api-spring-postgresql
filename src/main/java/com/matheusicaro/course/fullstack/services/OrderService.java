package com.matheusicaro.course.fullstack.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.Order;
import com.matheusicaro.course.fullstack.domain.OrderItem;
import com.matheusicaro.course.fullstack.domain.PaymentForTicket;
import com.matheusicaro.course.fullstack.enums.PaymentOptionENUM;
import com.matheusicaro.course.fullstack.repositories.OrderItemRespository;
import com.matheusicaro.course.fullstack.repositories.OrderRespository;
import com.matheusicaro.course.fullstack.repositories.PaymentRespository;
import com.matheusicaro.course.fullstack.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	OrderRespository orderRespository;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private PaymentRespository paymentRespository;
	
	@Autowired
	private OrderItemRespository orderItemRespository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Order findById(Integer id) {
		
		Optional<Order> order = orderRespository.findById(id);
		
		return order.orElseThrow(() -> new ObjectNotFoundException(
				"Não encontrado! " + "Id: " + id + ", Tipo: " + Order.class.getSimpleName()));		
	}

	public Order insert(Order order) {
		order.setId(null);
		order.setDate(new Date());
		order.setClient(clientService.findById(order.getClient().getId()));

		order.getPayment().setPaymentOption(PaymentOptionENUM.PENDING);
		order.getPayment().setOrder(order);
		if (order.getPayment() instanceof PaymentForTicket) {
			PaymentForTicket payment = (PaymentForTicket) order.getPayment();
			ticketService.fillInPaymentForTicketo(payment, order.getDate());
		}
		order = orderRespository.save(order);
		paymentRespository.save(order.getPayment());
		
		for (OrderItem item : order.getOrderItems()) {
			item.setDiscount(0.0);
			
			item.setProduct(productService.find(item.getProduct().getId()));
			item.setPrice(item.getProduct().getPrice());
			item.setOrder(order);
		}
		orderItemRespository.saveAll(order.getOrderItems());
		emailService.sendOrderConfirmationEmail(order);
		return order;
	}
}
