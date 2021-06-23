/*
Copyright [2021] [Alex Santos Fraga]
*/

package com.asf.desafioCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asf.desafioCrud.domain.Client;

@Repository
public  interface ClientRepository extends JpaRepository<Client, Integer>{

}
