package com.matheusicaro.course.fullstack.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.PaymentForTicket;

@Service
public class TicketService {
 	public void preencherPagamentoComBoleto(PaymentForTicket ticket, Date instanteDoPedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instanteDoPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		ticket.setExpiredDate(calendar.getTime());
	}

	public void fillInPaymentForTicketo(PaymentForTicket payment, Date orderDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orderDate);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		payment.setExpiredDate(calendar.getTime());
	}
}