/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@NotNull(message="Preenchimento Obrigatorio do tipo Double")
	private Double rent_price;
	
	//private List<RentedDto> rents = new ArrayList<>();

	public CarDto() {  super(); }
	
	public CarDto(Integer id, String brand, String model, Double rent_price) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.rent_price = rent_price;
	}
	
	public CarDto(Car car) {
		super();
		this.id =car.getId();
		this.brand = car.getBrand();
		this.model = car.getModel();
		this.rent_price = car.getRent_price();
		//this.setRents(param.getRents().stream().map( x -> new RentedDto(x) ).collect(Collectors.toList()));
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


	public Car parseObjDTOtoObj(CarDto param) {
		return new Car(  param.getId(), param.getBrand(), param.getModel(),  param.getRent_price() );
	}

}
