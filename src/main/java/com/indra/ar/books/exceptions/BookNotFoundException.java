package com.indra.ar.books.exceptions;

public class BookNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -8482739001310523692L;

	public BookNotFoundException(String message) {
		super(message);
	}
}
