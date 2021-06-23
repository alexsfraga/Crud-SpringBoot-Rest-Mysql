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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asf.desafioCrud.domain.Client;
import com.asf.desafioCrud.dto.ClientDto;
import com.asf.desafioCrud.service.ClientService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/client")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> select(@PathVariable Integer id) throws ObjectNotFoundException {
		Client obj = service.selectByID(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<?>  insert(@Valid @RequestBody ClientDto param) {
		Client obj = service.create(param);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(  value="/{id}"  , method = RequestMethod.PUT )
	public ResponseEntity<Client>  update(@Valid @RequestBody ClientDto param , @PathVariable Integer id ) {
		param.setId(id);
		Client obj = service.update(param);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(  value="/{id}"  , method = RequestMethod.DELETE )
	public ResponseEntity<?>  delete( @RequestBody Client param , @PathVariable Integer id ) {
		param.setId(id);
		service.delete(param);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(  value="/list"  , method = RequestMethod.GET )
	public ResponseEntity<Page<Client>>  findPage( 
				@RequestParam(value="page", defaultValue = "0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue = "name") String orderBy,
				@RequestParam(value="direction", defaultValue = "ASC") String direction ) {
			Page<Client> list = service.selectPage(page, linesPerPage, orderBy, direction);
			
			return ResponseEntity.ok().body(list);
	}
}
