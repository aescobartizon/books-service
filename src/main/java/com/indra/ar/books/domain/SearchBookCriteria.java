package com.indra.ar.books.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchBookCriteria implements Serializable{

	
	public SearchBookCriteria(String autor, String isbn, String titulo) {
		super();
		this.autor = autor;
		this.isbn = isbn;
		this.titulo = titulo;
	}

	public SearchBookCriteria() {
		super();
	}

	private static final long serialVersionUID = 1944808795258933315L;

	private String autor;
	
	private String isbn;
	
	private String titulo;
}
