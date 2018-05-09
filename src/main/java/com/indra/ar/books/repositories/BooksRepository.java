package com.indra.ar.books.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.indra.ar.books.entities.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long>{
	
	@Query(value = "SELECT * FROM BOOKS B WHERE B.titulo like  %?1%", nativeQuery = true)
    List<Books> findBooksBytitulo(String titulo);

}
