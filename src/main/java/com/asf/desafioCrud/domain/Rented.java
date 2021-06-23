/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


import com.fasterxml.jackson.annotation.JsonFormat;

public class Rented implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_RENTED")
	@SequenceGenerator(name = "SEQ_RENTED", sequenceName = "SEQ_RENTED", initialValue = 1, allocationSize = 1)
	private Integer id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date instante;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Client client = new Client();
	
	
	public Rented() { 	}
	

}
