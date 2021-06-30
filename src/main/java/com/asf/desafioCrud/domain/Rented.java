/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.asf.desafioCrud.dto.ItemRentedDto;
import com.asf.desafioCrud.dto.RentedDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Rented implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator = "gen_rented")
	@GenericGenerator(name = "gen_rented", strategy = "native")
	private Integer id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateStart;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateEnd;
	
	private Double total = 0.0;

	@ManyToOne
	@JoinColumn( name="client_id" )
	private Client client = new Client();
	
	@OneToMany( fetch = FetchType.LAZY , cascade = CascadeType.DETACH , mappedBy = "id.rented" )
	private List<ItemRented> itens = new ArrayList<>();
	
	public Rented() {  super(); }
	
	public Rented( Integer id, Client client, Date dateStart, Date dateEnd , Double total) {
		super();
		this.id = id;
		this.client = client;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.total = total;
	}
	
	public Rented( RentedDto rentedDto ) {
		super();
		this.id = rentedDto.getId();
		this.client = new Client( rentedDto.getClient() );
		this.dateStart = rentedDto.getDateStart();
		this.dateEnd = rentedDto.getDateEnd();
		this.total = rentedDto.getTotal();
		if( rentedDto.getItens().size()>0 ) {
			this.itens = rentedDto.getItens().stream().map( item -> new ItemRented( item ) ).collect(Collectors.toList());
			itens.stream().forEach(  x -> x.setRented( new Rented(id, client, dateStart, dateEnd, total) )   );
		}
	}

	public Integer getId() {
		return this.id;
	}
	//@JsonManagedReference(value="rented-client")
	public Client getClient() {
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
	
	//@JsonManagedReference(value="rented-itemRented")
	public List<ItemRented> getItens() {
		return itens;
	}
	
	public void setId( Integer id ){
		this.id = id;
	}
	
	public void setClient(Client client) {
		this.client = client;
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
		Rented other = (Rented) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	}
