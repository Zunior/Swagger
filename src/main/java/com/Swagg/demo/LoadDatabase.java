package com.Swagg.demo;

import com.Swagg.demo.module.pizza.dto.PizzaDto;
import com.Swagg.demo.module.pizza.service.GenericService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

	private static Logger log = LogManager.getLogger(LoadDatabase.class.getName());
//	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	private static GenericService<PizzaDto> pizzaService;
	
	@Autowired
	public LoadDatabase(GenericService<PizzaDto> pizzaService) {
		LoadDatabase.pizzaService = pizzaService;
	}
	
	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			log.debug("Preloading " + pizzaService.add(PizzaDto.builder("capricciosa").name("Capricciosa").size(240).price(20).build()));
			log.debug("Preloading " + pizzaService.add(PizzaDto.builder("calzone").name("Calzone").size(280).price(23).build()));
		};
	}
	
	

}
