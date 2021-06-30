/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.asf.desafioCrud.domain.ItemRented;
import com.asf.desafioCrud.domain.Rented;
import com.asf.desafioCrud.dto.RentedDto;
import com.asf.desafioCrud.repositories.ItemRentedRepository;
import com.asf.desafioCrud.repositories.RentedRepository;

@Service
public class RentedService {
	
	@Autowired
	private RentedRepository repo;
	
	@Autowired
	private ItemRentedRepository repoItem;
	
	public RentedDto selectByID(Integer id) {
		Optional<Rented> obj = repo.findById(id);
		if(obj.isEmpty()) {
			throw new com.asf.desafioCrud.service.exceptions.ObjectNotFoundException("Objeto não encontrado, id : " + id);
		}
		return new RentedDto( obj.get() );
	}
	
	public RentedDto selectRentedAndItens(Integer id){
		Optional<Rented> obj = repo.findById(id);
		RentedDto objDto = new RentedDto( obj.get() );
		List<ItemRented> itens = repoItem.findItensByRented( obj.get() );
		if(  itens.isEmpty()  ) {
			throw new com.asf.desafioCrud.service.exceptions.ObjectNotFoundException("Itens not Found for this Rented by id : " + id);
		}
		objDto.parseItensToDto(itens);
		
		return objDto;
	}
	
	public Rented create(RentedDto param) {
		return repo.save( new Rented(param) );
	}
	
	public RentedDto update(RentedDto param) {
		Rented obj = new Rented(param);
		Rented newObj = new Rented(  selectByID( param.getId())  );
			parseDataObj( newObj , obj );
		List<ItemRented> list = repoItem.findItensByRented(newObj);
		Double totalGeral = 0.0;
		if( list.size() > 0) {
			for ( ItemRented it : list ) {
				Double itemValorTotal =  it.getQuantidade() * it.getCar().getRent_price() ;
				totalGeral = totalGeral + itemValorTotal;
				it.setTotal_price_item(itemValorTotal);
			}
			newObj.setItens(list);
			newObj.setTotal(totalGeral);
		}
		return new RentedDto( repo.save(newObj) );
	}
	private void parseDataObj(Rented newObj, Rented obj){
		newObj.setClient(obj.getClient());
		newObj.setDateStart(obj.getDateStart());
		newObj.setDateEnd(obj.getDateEnd());
	}
	
	public boolean delete( Integer id ) {
		boolean deleted = false;
		RentedDto obj = selectByID( id );
		try {
			repo.delete( new Rented(obj) );
		}catch(DataIntegrityViolationException e) {
			throw new com.asf.desafioCrud.service.exceptions.DataIntegrityException("Não é possivel excluir o RentedDto - exception of integrity");
		}
		
		if(  !repo.existsById(id)) {
			deleted = true;
		}
		
		return deleted;
	}
	
	public Page<RentedDto> selectPage( Integer page, Integer linesPerPage, String orderBy, String direction ){
		PageRequest pageRequest = PageRequest.of( page , linesPerPage , Direction.valueOf(direction), orderBy);
		Page pg = repo.findAll(pageRequest);
		List<?> list = pg.getContent();
		list = list.stream().map( x ->  new RentedDto( (Rented)x ) ).collect(Collectors.toList());
		return new PageImpl( list);
	}
	

	
}
