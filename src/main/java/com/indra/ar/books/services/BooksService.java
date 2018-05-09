package com.indra.ar.books.services;

import java.util.List;

import com.indra.ar.books.domain.LibroDTO;
import com.indra.ar.books.domain.SearchBookCriteria;

public interface BooksService extends CrudService<LibroDTO>{

	
	List<LibroDTO> findBookByTitulo(SearchBookCriteria searchBookCriteria);
	
	List<LibroDTO> findBookByCriteria(SearchBookCriteria searchBookCriteria);
}
