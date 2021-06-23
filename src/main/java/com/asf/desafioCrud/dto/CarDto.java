/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.asf.desafioCrud.domain.Car;

public class CarDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Length(min=2, max= 120, message="O tamanho deve ser entre 2 e 120 caracteres")
	private String brand;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Length(min=1, max= 120, message="O tamanho deve ser entre 1 e 120 caracteres")
	private String model;
	
	//@NotEmpty(message="Preenchimento Obrigatorio do tipo Double")
	@NotNull(message="Preenchimento Obrigatorio do tipo Double")
	private Double rent_price;

	public CarDto() {}
	
	public CarDto(Car param) {
		this.id =param.getId();
		this.brand = param.getBrand();
		this.model = param.getModel();
		this.rent_price = param.getRentPrice();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getRent_price() {
		return rent_price;
	}

	public void setRent_price(Double rent_price) {
		this.rent_price = rent_price;
	}
	public void setRent_price(String rent_price) {
		this.rent_price = Double.parseDouble(rent_price);
	}
	
}
