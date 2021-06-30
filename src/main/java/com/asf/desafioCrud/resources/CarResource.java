/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asf.desafioCrud.domain.Car;
import com.asf.desafioCrud.domain.Client;
import com.asf.desafioCrud.dto.CarDto;
import com.asf.desafioCrud.service.CarService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/car")
public class CarResource {
	
	@Autowired
	private CarService service;
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	public ResponseEntity<?> select( @PathVariable Integer id ) throws ObjectNotFoundException {
		Car obj = service.selectByID(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<?>  insert(@Valid @RequestBody CarDto param) {
		Car obj = service.create(param);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(  value="/{id}"  , method = RequestMethod.PUT )
	public ResponseEntity<Car>  update(@Valid @RequestBody CarDto param , @PathVariable Integer id ) {
		param.setId(id);
		Car obj = service.update(param);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(  value="/{id}"  , method = RequestMethod.DELETE )
	public ResponseEntity<?>  delete(  @PathVariable Integer id ) {
		Car obj = new Car(id, null, null, null);
		//param.setId(id);
		service.delete(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(  value="/list"  , method = RequestMethod.GET )
	public ResponseEntity<Page<Car>>  findPage( 
				@RequestParam(value="page", defaultValue = "0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue = "model") String orderBy,
				@RequestParam(value="direction", defaultValue = "ASC") String direction ) {
			Page<Car> list = service.selectPage(page, linesPerPage, orderBy, direction);
		
			return ResponseEntity.ok().body(list);
	}
}
