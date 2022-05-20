package com.Swagg.demo.module.pizza.controller;

import com.Swagg.demo.exception.PizzaExistsException;
import com.Swagg.demo.module.pizza.dto.PizzaDto;
import com.Swagg.demo.module.pizza.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({ "/pizzaFactory" })
@Tag(name = "PizzaFactory", description = "Pizza factory API")
public class PizzaController {

	private final GenericService<PizzaDto> pizzaService;

	@Autowired
	public PizzaController(GenericService<PizzaDto> pizzaService) {
		this.pizzaService = pizzaService;
	}

	@GetMapping("/pizzas")
	@Operation(summary = "Find all pizzas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK")})
	@ResponseStatus(HttpStatus.OK)
	List<PizzaDto> findAll() {

		return pizzaService.getAll();
	}

	@GetMapping("/{slug}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Find pizza by slug")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
							@ApiResponse(responseCode = "404", description = "Pizza not found")})
	PizzaDto load(@RequestParam("slug") String slug) throws Exception {
		return pizzaService.get(slug);
	}

	@GetMapping("/searchByName")
	@ResponseStatus(HttpStatus.OK)
	List<PizzaDto> searchByName(@RequestParam("name") String name) {
		return pizzaService.searchByName(name);
	}

	@PostMapping("/save")
	PizzaDto save(@Valid @RequestBody PizzaDto pizzaDto) throws PizzaExistsException {
		return pizzaService.add(pizzaDto);
	}

	@PostMapping("/update")
	void update(@Valid @RequestBody PizzaDto pizzaDto) throws PizzaExistsException {
		pizzaService.update(pizzaDto);
	}
	
	@PutMapping("/replace/{slug}")
	void replace(@Valid @RequestBody PizzaDto pizzaDto, @PathVariable String slug) throws PizzaExistsException {
		pizzaService.replace(pizzaDto, slug);
	}

	@DeleteMapping("/{slug}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	void delete(@RequestParam("slug") String slug) {
		try {
			pizzaService.delete(slug);
		} catch (DataIntegrityViolationException ex) {
			throw new PersistenceException("Item can not be deleted", ex);
		}
	}

}
