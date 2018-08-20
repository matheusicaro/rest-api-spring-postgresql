package com.matheusicaro.course.fullstack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.matheusicaro.course.fullstack.enums.ClientType;


@Entity
@Table(name="_CLIENT")
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String CPF_CNPJ;
	private Integer type;
	
	@OneToMany()
	@JoinColumn(name = "client_id")
	private List<HouseAndress> houseAndress = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="PHONE")
	private Set<String> phones = new HashSet<>();

	public Client() {
		
	}
	
	public Client(Integer id, String name, String email, String cPF_CNPJ, Integer type,
			List<HouseAndress> houseAndress) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		CPF_CNPJ = cPF_CNPJ;
		this.type = type;
		this.houseAndress = houseAndress;
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
		return CPF_CNPJ;
	}

	public void setCPF_CNPJ(String cPF_CNPJ) {
		CPF_CNPJ = cPF_CNPJ;
	}

	public ClientType getType() {
		return ClientType.toEnum(type);
	}

	public void setType(ClientType type) {
		this.type = type.getCode();
	}

	public List<HouseAndress> getHouseAndress() {
		return houseAndress;
	}

	public void setHouseAndress(List<HouseAndress> houseAndress) {
		this.houseAndress = houseAndress;
	}
	
	
	
}
