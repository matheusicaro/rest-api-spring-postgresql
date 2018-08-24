package com.matheusicaro.course.fullstack.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="_ORDER_ITEM")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id_OrderItemPK = new OrderItemPK();
	
	private double discount;
	private Integer	amount;
	private double price;
	
	public OrderItem() {

	}

	public OrderItem(Order order, Product product, double discount, Integer amount, double price) {
		super();
		this.discount = discount;
		this.amount = amount;
		this.price = price;
		this.id_OrderItemPK.setProduct(product);
		this.id_OrderItemPK.setOrder(order);
	}
	
	public Product getProduct() {
		return id_OrderItemPK.getProduct();
	}
	
	public Order getOrder() {
		return id_OrderItemPK.getOrder();
	}
	
	public OrderItemPK getId_OrderItemPK() {
		return id_OrderItemPK;
	}
	public void setId_OrderItemPK(OrderItemPK id_OrderItemPK) {
		this.id_OrderItemPK = id_OrderItemPK;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_OrderItemPK == null) ? 0 : id_OrderItemPK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id_OrderItemPK == null) {
			if (other.id_OrderItemPK != null)
				return false;
		} else if (!id_OrderItemPK.equals(other.id_OrderItemPK))
			return false;
		return true;
	}
	
	
}
