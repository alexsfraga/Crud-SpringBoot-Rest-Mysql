/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.dto;

import java.io.Serializable;

import com.asf.desafioCrud.domain.Car;
import com.asf.desafioCrud.domain.ItemRented;
import com.asf.desafioCrud.domain.Rented;

public class ItemRentedDto implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String id[] = new String[2] ;
		private int quantidade;
		private Double total_price_item;
		private CarDto car = new CarDto();
		
		public ItemRentedDto() {  super();	}
		
		public ItemRentedDto( ItemRented param) { 
			super();
			this.id[0] = "idRented="+ Integer.toString( param.getRented().getId()) ;
			this.id[1] = "idCar=" + Integer.toString( param.getCar().getId() );
			this.car =  new CarDto( param.getCar() );		
			this.quantidade = param.getQuantidade();
			this.total_price_item = param.getTotal_price_item();
		}
	
		public String[] getId() {
			return id;
		}
		public void setId(Rented rented, Car car) {
			this.id[0] = "idRented="+ Integer.toString( rented.getId());
			this.id[1] = "idCar=" + Integer.toString( car.getId() );
		}
	
		public CarDto getCar() {
			return this.car;
		}
		
		public int getQuantidade() {
			return quantidade;
		}
	
		public Double getTotal_price_item() {
			return total_price_item;
		}
	
		public void setRented( RentedDto rentedDto ) {
			this.id[0] = "idRented="+ Integer.toString( rentedDto.getId()) ;
		}
		
		public void setCar( CarDto car ) {
			this.id[1] = "idCar=" + Integer.toString( car.getId() );
			this.car = car;
		}
		
		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}
		
		public void setTotal_price_item(Double totalprice) {
			this.total_price_item = totalprice;
		}

	
	}


