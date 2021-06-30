/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.asf.desafioCrud.domain.Client;
import com.asf.desafioCrud.domain.ItemRented;
import com.asf.desafioCrud.domain.Rented;
import com.fasterxml.jackson.annotation.JsonFormat;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

public class RentedDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message="Preenchimento Obrigatorio")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateStart;
	
	@NotNull(message="Preenchimento Obrigatorio")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateEnd;
	
	private Double total = 0.0;
	
	@NotNull(message="Preenchimento Obrigatorio")
	private ClientDto client;
	
	private List<ItemRentedDto> itens = new ArrayList<ItemRentedDto>();
	
	public RentedDto() {  super();	}
	
	public RentedDto( Rented rented ) { 
		super();
		this.id = rented.getId();
		this.client = new ClientDto( rented.getClient() );
		this.dateStart = rented.getDateStart();
		this.dateEnd = rented.getDateEnd();
		this.total = rented.getTotal();
		if( rented.getItens().size() > 0) {
			parseItensToDto( rented.getItens() );
		}
	}
	
	/*
	  	public RentedDto( Integer id, Client client, Date dateStart, Date dateEnd, Double total) {
		super();
		this.id = id;
		this.client = new ClientDto( client );
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.total = total;
	}
	*/
	
	public Integer getId() {
		return this.id;
	}
	
	public ClientDto getClient() {
		return this.client;
	}
	
	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}
	
	public Double getTotal() {
		return total;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setClient(Client client) {
		this.client = new ClientDto( client );
	}
	
	public void set_DtoClient(ClientDto client) {
		this.client = client ;
	}
	
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	
	@IgnoreForBinding
	public List<ItemRentedDto> getItens() {
		return itens;
	}

	public void setItens( List<ItemRentedDto> itens) {
		this.itens = itens;
	}
	
	public void parseItensToDto( List<ItemRented> pItens ) {
		//List list = new ArrayList<>( itens );
		if( pItens.size() > 0 ) {
			this.itens = (List) pItens.stream().map( ix ->  new ItemRentedDto( (ItemRented) ix) ).collect(Collectors.toList( )  );
		}
	}
		
}
