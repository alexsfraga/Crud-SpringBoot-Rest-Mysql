/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.asf.desafioCrud.dto.CarDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_car")
	@GenericGenerator(name = "gen_car", strategy = "native")
	private Integer id;
	
	private String brand;
	private String model;
	private Double rent_price;
	
	@OneToMany(mappedBy = "id.car")
	private List<ItemRented> itens = new ArrayList<>();

	public Car() { super(); }
	
	public Car(Integer id, String brand, String model, Double rent_price) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.rent_price = rent_price;
	}
	
	public Car( CarDto carDto) {
		super();
		this.id = carDto.getId();
		this.brand = carDto.getBrand();
		this.model = carDto.getModel();
		this.rent_price = carDto.getRent_price();
	}

	@JsonIgnore
	@JsonManagedReference(value="car-itemRented")
	public List<ItemRented> getItens() {
		return itens;
	}
	
	public Integer getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public Double getRent_price() {
		return rent_price;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setBrand(String marca) {
		this.brand = marca;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setRent_price(Double rent_price) {
		this.rent_price = rent_price;
	}
	public void setItens(List<ItemRented> itens) {
		this.itens = itens;
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
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
