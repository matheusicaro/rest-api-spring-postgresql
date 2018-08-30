package com.matheusicaro.course.fullstack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheusicaro.course.fullstack.enums.ClientTypeENUM;


@Entity
@Table(name="_CLIENT")
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpf_cnpj;
	private Integer type;
	
	@ElementCollection
	@CollectionTable(name="_PHONE")
	private Set<String> phones = new HashSet<>();
	
	@OneToMany(mappedBy = "client", cascade=CascadeType.ALL)
	private List<AddressHouse> houseAndress = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public Client() {
		
	}
	
	public Client(Integer id, String name, String email, String cPF_CNPJ, ClientTypeENUM type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf_cnpj = cPF_CNPJ;
		this.type = (type == null) ? null : type.getCode();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCPF_CNPJ() {
		return cpf_cnpj;
	}

	public void setCPF_CNPJ(String cPF_CNPJ) {
		cpf_cnpj = cPF_CNPJ;
	}

	public ClientTypeENUM getType() {
		return ClientTypeENUM.toEnum(type);
	}

	public void setType(ClientTypeENUM type) {
		this.type = type.getCode();
	}

	public List<AddressHouse> getHouseAndress() {
		return houseAndress;
	}

	public void setHouseAndress(List<AddressHouse> houseAndress) {
		this.houseAndress = houseAndress;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
