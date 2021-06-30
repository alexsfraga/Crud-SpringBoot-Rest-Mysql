package com.asf.desafioCrud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asf.desafioCrud.domain.Car;
import com.asf.desafioCrud.domain.Client;
import com.asf.desafioCrud.domain.ItemRented;
import com.asf.desafioCrud.domain.Rented;
import com.asf.desafioCrud.repositories.CarRepository;
import com.asf.desafioCrud.repositories.ClientRepository;
import com.asf.desafioCrud.repositories.ItemRentedRepository;
import com.asf.desafioCrud.repositories.RentedRepository;

@SpringBootApplication
public class DesafioCrudApplication implements CommandLineRunner {
	
	@Autowired
	ClientRepository cliRepo;
	
	@Autowired
	CarRepository carRepo;
	
	@Autowired
	RentedRepository renRepo;
	
	@Autowired
	ItemRentedRepository itemRepo;

	public static void main(String[] args) {
		SpringApplication.run(DesafioCrudApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		setData( );
	}

	public void setData() throws ParseException, java.text.ParseException{
		
			int x =0;
			
			Car ca1 = new Car(null , "Fiat", "500", 900.12);
			Car ca2= new Car(null , "Chevrollet", "Chevete", 15.12);
			Car ca3 = new Car(null , "Ford", "T", 5900.12);
			Car ca4 = new Car(null , "vw", "gol bx", 150.12);
			
			Client cli1 = new Client(null , "Joao" , "joao@teste.com");
			Client cli2 = new Client(null , "Maria" , "joao@teste.com");
			Client cli3 = new Client(null , "Maria Joao" , "joao@teste.com");
			Client cli4 = new Client(null , "Maria Rosa" , "joao@teste.com");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dt1 = "2021-01-01 00:00:01" ;
			String dt2 = "2021-01-30 23:59:59" ;
			Rented ren1 = new Rented(null , cli2 , sdf.parse(dt1), sdf.parse(dt2), null );
			Rented ren2 = new Rented(null , cli3 , sdf.parse(dt1), sdf.parse(dt2), null );
			Rented ren3 = new Rented(null , cli1 , sdf.parse(dt1), sdf.parse(dt2), null );
			Rented ren4 = new Rented(null , cli4 , sdf.parse(dt1), sdf.parse(dt2), null );
			
			ItemRented it1 = new ItemRented(ren1, ca2, 2, (ca2.getRent_price()* 2) );
			ItemRented it2 = new ItemRented(ren1, ca1, 1, (ca1.getRent_price()* 1) );
			ItemRented it3 = new ItemRented(ren2, ca3, 2, (ca3.getRent_price()* 2) );
			ItemRented it4 = new ItemRented(ren3, ca4, 1, (ca4.getRent_price()* 1) );
			ItemRented it5 = new ItemRented(ren4, ca2, 1, (ca2.getRent_price()* 1) );
			
			ren1.getItens().addAll( Arrays.asList( it1, it2  )  );
			ren2.getItens().add(  it3 );
			ren3.getItens().addAll( Arrays.asList( it4 )  );
			ren4.getItens().addAll( Arrays.asList( it5 )  );
					
			ca1.getItens().addAll(  Arrays.asList( it2));
			ca2.getItens().addAll(  Arrays.asList( it1 , it5 ));
			ca3.getItens().addAll(  Arrays.asList( it3 ));
			ca4.getItens().addAll(  Arrays.asList( it4));

			List<Rented> li = new ArrayList<>();
			li.addAll( Arrays.asList( ren1, ren2, ren3, ren4));
			for (Rented rented : li) {
				List<ItemRented> itList = rented.getItens();
				Double totalGeral = 0.0;
				if( itList.size() > 0) {
					for ( ItemRented it : itList ) {
						Double itemValorTotal =  it.getQuantidade() * it.getCar().getRent_price() ;
						totalGeral = totalGeral + itemValorTotal;
						it.setTotal_price_item(itemValorTotal);
					}
					rented.setItens( itList );
					rented.setTotal(totalGeral);
				}			
			}

			
			if( x == 1 ){
				cliRepo.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
				carRepo.saveAll(Arrays.asList(ca1, ca2, ca3, ca4 ));
				renRepo.saveAll(Arrays.asList(ren1, ren2, ren3, ren4 ));
				itemRepo.saveAll(Arrays.asList( it1, it2, it3, it4, it5));
			}

		
	}

	
}
