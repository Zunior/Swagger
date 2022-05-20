package com.Swagg.demo.module.pizza.service;

import com.Swagg.demo.module.pizza.dto.PizzaDto;

import java.util.List;

public interface GenericService<T> {
	public List<T> getAll();

	public T get(String id) throws Exception;

	public List<T> searchByName(String name);

	public T add(T t);

	public void update(T t);
	
	public PizzaDto replace(PizzaDto pizzaDto, String slug);

	public void delete(String id);
}
