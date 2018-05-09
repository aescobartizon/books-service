package com.indra.ar.books.repositories;

import java.util.List;

import com.indra.ar.books.domain.SearchBookCriteria;
import com.indra.ar.books.entities.Books;

public interface BooksDao {

	List<Books> searchBooksByCriteria(SearchBookCriteria searchCriteria);
}
