package com.asf.desafioCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.asf.desafioCrud.domain.Client;
import com.asf.desafioCrud.repositories.ClientRepository;

@SpringBootApplication
public class DesafioCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCrudApplication.class, args);
	}

}
