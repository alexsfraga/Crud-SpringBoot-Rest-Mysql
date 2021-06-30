/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asf.desafioCrud.domain.Rented;

@Repository
public  interface RentedRepository extends JpaRepository<Rented, Integer>{

}
