/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Embeddable
public class ItemRentedPk implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn( name= "rented_id" )
	private Rented rented;
	
	@ManyToOne
	@JoinColumn( name = "car_id" )
	private Car car;
	
	public ItemRentedPk() {}

	@JsonBackReference( value="rented-itemRented")
	public Rented getRented() {
		return rented;
	}

	public void setRented(Rented rented) {
		this.rented = rented;
	}
	@JsonBackReference(value="car-itemRented")
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((rented == null) ? 0 : rented.hashCode());
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
		ItemRentedPk other = (ItemRentedPk) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (rented == null) {
			if (other.rented != null)
				return false;
		} else if (!rented.equals(other.rented))
			return false;
		return true;
	}
	
	

}
