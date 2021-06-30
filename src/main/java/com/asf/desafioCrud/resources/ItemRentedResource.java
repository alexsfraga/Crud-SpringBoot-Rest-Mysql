/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asf.desafioCrud.domain.Car;
import com.asf.desafioCrud.domain.ItemRented;
import com.asf.desafioCrud.domain.ItemRentedPk;
import com.asf.desafioCrud.domain.Rented;
import com.asf.desafioCrud.dto.RentedDto;
import com.asf.desafioCrud.service.ItemRentedService;
import com.asf.desafioCrud.service.RentedService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/item")
public class ItemRentedResource {
	
	@Autowired
	private ItemRentedService service;
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	public ResponseEntity<?> select( @RequestBody ItemRentedPk param , @PathVariable Integer id) throws ObjectNotFoundException {
		
		ItemRented obj = service.selectByID(param) ;
		
		return ResponseEntity.ok().body(obj );
	}
	
	@RequestMapping(value="/byrented/{id}",  method=RequestMethod.GET)
	public ResponseEntity<?> selectByRented(  @PathVariable Integer id) throws ObjectNotFoundException {
		List obj = service.selectByRentedId(id);
		
		return ResponseEntity.ok().body(obj );
	}
	@RequestMapping(value="/bycar/{id}",  method=RequestMethod.GET)
	public ResponseEntity<?> selectByCar(  @PathVariable Integer id) throws ObjectNotFoundException {
		List obj = service.selectByCarId(id);
		
		return ResponseEntity.ok().body(obj );
	}
	
	@RequestMapping(  value="/list"  , method = RequestMethod.GET )
	public ResponseEntity<Page<ItemRented>>  findPage( 
				@RequestParam(value="page", defaultValue = "0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue = "id") String orderBy,
				@RequestParam(value="direction", defaultValue = "ASC") String direction ) {
			Page<ItemRented> list = service.selectPage(page, linesPerPage, orderBy, direction);
		
			return ResponseEntity.ok().body(list);
	}
	

}
