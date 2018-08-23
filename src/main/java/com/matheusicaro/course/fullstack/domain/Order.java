package com.matheusicaro.course.fullstack.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="_ORDER")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
			
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date orderDate;
				
	@OneToOne(cascade = CascadeType.ALL, mappedBy="order") 
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "houseAddress_id")
	private HouseAddress houseAddress;

	public Order() {

	}
	public Order(Integer id, Date orderDate, Client client, HouseAddress houseAddress) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.client = client;
		this.houseAddress = houseAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public HouseAddress getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(HouseAddress houseAddress) {
		this.houseAddress = houseAddress;
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
	
	
	
}
