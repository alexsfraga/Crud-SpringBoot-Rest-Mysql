/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.asf.desafioCrud.dto.ItemRentedDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ItemRented implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemRentedPk id = new ItemRentedPk();
	
	private int quantidade = 0;
	private Double total_price_item = 0.0;

	public ItemRented() { super();	}

	public ItemRented(Rented rented, Car car, int quantidade, Double totalprice) {
		super();
		this.id.setRented(rented);
		this.id.setCar(car);
		this.quantidade = quantidade;
		this.total_price_item = totalprice;
	}
	
	public ItemRented( ItemRentedDto itemRentedDto ) {
		super();
		//String rented_id = itemRentedDto.getId()
		this.id.setRented( new Rented( ) );
		this.id.setCar( new Car( itemRentedDto.getCar() ) );
		this.quantidade = itemRentedDto.getQuantidade();
		this.total_price_item = itemRentedDto.getTotal_price_item();
	}

	public ItemRentedPk getId() {
		return this.id;
	}
	
	@JsonBackReference(value="rented-itemRented")
	public Rented getRented() {
		return id.getRented();
	}

	public void setRented(Rented rent) {
		this.id.setRented(rent);
	}
	
	//@JsonBackReference(value="car-itemRented")
	public Car getCar() {
		return id.getCar();
	}
	
	public void setCar( Car car ) {
		this.id.setCar(car);
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getTotal_price_item() {
		return total_price_item;
	}

	public void setTotal_price_item(Double totalprice) {
		this.total_price_item = totalprice;
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
		ItemRented other = (ItemRented) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	}


