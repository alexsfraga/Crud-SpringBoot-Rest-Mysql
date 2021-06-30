/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.asf.desafioCrud.domain.Car;
import com.asf.desafioCrud.domain.Client;
import com.asf.desafioCrud.domain.ItemRented;
import com.asf.desafioCrud.domain.ItemRentedPk;
import com.asf.desafioCrud.domain.Rented;
import com.asf.desafioCrud.dto.ClientDto;
import com.asf.desafioCrud.dto.ItemRentedDto;
import com.asf.desafioCrud.repositories.ClientRepository;
import com.asf.desafioCrud.repositories.ItemRentedRepository;
import com.asf.desafioCrud.service.exceptions.DataIntegrityException;

@Service
public class ItemRentedService {
	
	@Autowired
	private ItemRentedRepository repo;

	public ItemRented selectByID(ItemRentedPk id) {
		
		ItemRentedPk idpk = new ItemRentedPk();
		
		Optional<ItemRented> obj = repo.findById( idpk );
		if(obj.isEmpty()) {
			throw new com.asf.desafioCrud.service.exceptions.ObjectNotFoundException("Objeto não encontrado, id : " + id);
		}
		
		return  obj.get() ;
	}
	
	public 	List<?> selectByRentedId(Integer id) {
		Rented obj = new Rented(id, null, null, null, null);
		List<?> list = repo.findItensByRented( obj );
		list = list.stream().map( x -> new ItemRentedDto( (ItemRented) x ) ).collect(Collectors.toList());
		return list;
	}
	
	public 	List<?> selectByCarId(Integer id) {
		Car obj = new Car(id, null, null,null );
		List<?> list = repo.findItensByCar( obj );
		list = list.stream().map( x -> new ItemRentedDto( (ItemRented) x ) ).collect(Collectors.toList());
		return list;
	}
	
	public Page<ItemRented> selectPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of( page , linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
}
