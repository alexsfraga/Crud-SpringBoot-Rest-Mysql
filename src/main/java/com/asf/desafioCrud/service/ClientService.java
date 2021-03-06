/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.asf.desafioCrud.domain.Client;
import com.asf.desafioCrud.dto.ClientDto;
import com.asf.desafioCrud.repositories.ClientRepository;
import com.asf.desafioCrud.service.exceptions.DataIntegrityException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;

	public ClientDto selectByID(Integer id) {
		Optional<Client> obj = repo.findById(id);
		if(obj.isEmpty()) {
			throw new com.asf.desafioCrud.service.exceptions.ObjectNotFoundException("Objeto não encontrado, id : " + id);
		}
		
		return new ClientDto( obj.get() );
	}
	
	public List<Client> selectAll(){
		return repo.findAll();
	}
	public ClientDto create(Client param) {
		//ClientDto obj = new Client(repo.save(param));
		return new ClientDto( repo.save(param) );
	}
	public Client update(ClientDto param) {
		Client obj = this.parseObjDTOtoObj( param );
		Client newObj = this.parseObjDTOtoObj( selectByID( obj.getId() ) );
			parseDataObj( newObj , obj );
		return repo.save(newObj);
	}
	private void parseDataObj(Client newObj, Client obj){
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public void delete(Client param) {
		selectByID(param.getId());
		try {
			repo.delete(param);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o Client - exception of integrity");
		}
	}
	
	public Page<Client> selectPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of( page , linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client parseObjDTOtoObj(ClientDto param) {
		return new Client(  param.getId(), param.getName(), param.getEmail() );
	}
	
}
