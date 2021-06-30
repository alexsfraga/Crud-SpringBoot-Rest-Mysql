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

import com.asf.desafioCrud.domain.Car;
import com.asf.desafioCrud.dto.CarDto;
import com.asf.desafioCrud.repositories.CarRepository;
import com.asf.desafioCrud.service.exceptions.DataIntegrityException;

@Service
public class CarService {
	
	@Autowired
	private CarRepository repo;

	public Car selectByID(Integer id) {
		Optional<Car> obj = repo.findById(id);
		if(obj.isEmpty()) {
			throw new com.asf.desafioCrud.service.exceptions.ObjectNotFoundException("Objeto não encontrado, id : " + id);
		}
		return obj.get();
	}
	
	public List<Car> selectAll(){
		return repo.findAll();
	}
	public Car create(CarDto param) {
		Car obj = this.parseObjDTOtoObj(param);
		return repo.save(obj);
	}
	
	// method to create and update object
	public Car update(CarDto param) {
		Car obj = parseObjDTOtoObj(param);
		Car newObj =  selectByID(  obj.getId() );
			parseDataObj(newObj, obj );
		return repo.save(newObj);
	}
	private void parseDataObj(Car newObj, Car obj){
		newObj.setBrand(obj.getBrand());
		newObj.setModel(obj.getModel());
		newObj.setRent_price(obj.getRent_price());
	}
	
	public void delete(Car param) {
		selectByID(param.getId());
		try {
			repo.delete(param);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o Car - exception of integrity");
		}
	}
	
	public Page<Car> selectPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of( page , linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Car parseObjDTOtoObj(CarDto param) {
		return new Car(  param.getId(), param.getBrand(), param.getModel(),  param.getRent_price() );
	}

}
