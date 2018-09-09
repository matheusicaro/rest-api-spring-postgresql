package com.matheusicaro.course.fullstack.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "_ORDER")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date orderDate;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "houseAddress_id")
	private AddressHouse houseAddress;

	@OneToMany(mappedBy = "id_OrderItemPK.order")
	private Set<OrderItem> orderItems = new HashSet<>();

	public Order() {

	}

	public Order(Integer id, Date orderDate, Client client, AddressHouse houseAddress) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.client = client;
		this.houseAddress = houseAddress;
	}

	public double getTotalValue() {
		double total = 0;

		for (OrderItem item : orderItems) {
			total += item.getTotalSub();
		}

		return total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public Date getDate() {
		return orderDate;
	}

	public void setDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public AddressHouse getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(AddressHouse houseAddress) {
		this.houseAddress = houseAddress;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(dateFormat.format(getDate()));
		builder.append(", Cliente: ");
		builder.append(getClient().getName());
		builder.append(", Situação do pagamento: ");
		builder.append(getPayment().getPaymentOption().getDescription());
		builder.append("\nDetalhes:\n");
		for (OrderItem item : getOrderItems()) {
			builder.append(item.toString());
		}
		builder.append("Valor total: ");
		builder.append(format.format(getTotalValue()));
		return builder.toString();
	}

}
