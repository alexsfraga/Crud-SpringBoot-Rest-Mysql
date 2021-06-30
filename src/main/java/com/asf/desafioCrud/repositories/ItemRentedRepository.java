/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asf.desafioCrud.domain.Car;
import com.asf.desafioCrud.domain.ItemRented;
import com.asf.desafioCrud.domain.ItemRentedPk;
import com.asf.desafioCrud.domain.Rented;

@Repository
public  interface ItemRentedRepository extends JpaRepository<ItemRented, ItemRentedPk>{
	
	//@Query( "SELECT obj FROM ItemRented obj JOIN FETCH obj.id.Car where obj.id.Rented IN rented_id" )
	@Query( "SELECT obj FROM ItemRented obj WHERE obj.id.rented IN :param" )
	List<ItemRented> findItensByRented( Rented param );
	
	@Query( "SELECT obj FROM ItemRented obj WHERE obj.id.car IN :param" )
	List<ItemRented> findItensByCar( Car param );


}
