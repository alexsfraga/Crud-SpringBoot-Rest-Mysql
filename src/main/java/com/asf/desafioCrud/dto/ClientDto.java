package com.asf.desafioCrud.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.asf.desafioCrud.domain.Client;

public class ClientDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Length(min=5, max= 120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Email(message="Email invalido")
	private String email;
	
	//private List<RentedDto> rents = new ArrayList<>();
	
	public  ClientDto() { super();}
	
	public ClientDto(Integer id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public ClientDto(Client param) {
		super();
		this.id = param.getId();
		this.name = param.getName();
		this.email = param.getEmail();
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

}
