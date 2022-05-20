package com.Swagg.demo.exception;

import com.Swagg.demo.util.ExceptionConstants;

public class PizzaExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PizzaExistsException() {
		super(ExceptionConstants.PIZZA_EXIST);
	}

	public String getExceptionMessage() {
		return new PizzaExistsException().getMessage();
	}

}
