package com.indra.ar.books.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.indra.ar.books.domain.SearchBookCriteria;
import com.indra.ar.books.entities.Books;

import lombok.Getter;

@Component
public class BooksDaoImp implements BooksDao{
	
	@Getter
	@PersistenceContext
    EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Books> searchBooksByCriteria(SearchBookCriteria searchCriteria) {
		
		String queryStrig ="Select * from books b where b.isbn =:isbn";
		Query query = getEntityManager().createNativeQuery(queryStrig,Books.class);
		query.setParameter("isbn", searchCriteria.getIsbn());
		return query.getResultList();	
	} 

}
