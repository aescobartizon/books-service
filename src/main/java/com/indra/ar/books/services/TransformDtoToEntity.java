package com.indra.ar.books.services;

import org.springframework.stereotype.Service;

import com.indra.ar.books.domain.LibroDTO;
import com.indra.ar.books.domain.LibroDTOImp;
import com.indra.ar.books.entities.Books;

@Service
public class TransformDtoToEntity {

	public Books libroDtoToBooks(LibroDTO libro) {
		
		return Books.builder().autor(libro.getAutor()).titulo(libro.getTitulo())
				.fechaPublicacion(libro.getFechaPublicacion()).isbn(libro.getIsbn())
				.id(libro.getId()).build();
	}
	
	public LibroDTO booksToLibroDto(Books book) {
		
		return LibroDTOImp.builder().autor(book.getAutor()).titulo(book.getTitulo())
				.fechaPublicacion(book.getFechaPublicacion()).isbn(book.getIsbn())
				.id(book.getId()).build();
	}
}
