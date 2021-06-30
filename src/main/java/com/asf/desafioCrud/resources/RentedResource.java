/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asf.desafioCrud.domain.Rented;
import com.asf.desafioCrud.dto.RentedDto;
import com.asf.desafioCrud.service.RentedService;
import com.asf.desafioCrud.service.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/rented")
public class RentedResource {
	
	@Autowired
	private RentedService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> selectRented(@PathVariable Integer id) throws ObjectNotFoundException {
		RentedDto obj = service.selectByID(id);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(value="/{id}/itens", method=RequestMethod.GET)
	public ResponseEntity<?> selectRentedAndItens(@PathVariable Integer id) throws ObjectNotFoundException {
		RentedDto obj = service.selectRentedAndItens(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(  value="/list"  , method = RequestMethod.GET )
	public ResponseEntity<Page<RentedDto>>  selectPage( 
				@RequestParam(value="page", defaultValue = "0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue = "id") String orderBy,
				@RequestParam(value="direction", defaultValue = "ASC") String direction ) {
			Page<RentedDto> list = service.selectPage(page, linesPerPage, orderBy, direction);
			
			return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<?>  insert(@Valid @RequestBody RentedDto param) {
		System.out.println( param.toString() );
		Rented obj = service.create(param);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(  value="/{id}"  , method = RequestMethod.PUT )
	public ResponseEntity<?>  update(@Valid @RequestBody RentedDto param , @PathVariable Integer id ) {
		param.setId(id);
		RentedDto objDto =  service.update(param) ;
		return ResponseEntity.ok().body(objDto);
		//return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(  value="/{id}"  , method = RequestMethod.DELETE )
	public ResponseEntity<?>  delete( @PathVariable Integer id ) throws DataIntegrityException {
		ResponseEntity<?> rp = new ResponseEntity<>( HttpStatus.NO_CONTENT );
		if( service.delete( id ) ) {
			rp = new ResponseEntity<>( HttpStatus.OK );
		}
		return rp;
		//return ResponseEntity.noContent().build();
	}
}
