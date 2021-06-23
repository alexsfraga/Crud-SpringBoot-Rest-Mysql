/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asf.desafioCrud.domain.Car;

@Repository
public  interface CarRepository extends JpaRepository<Car, Integer>{

}
