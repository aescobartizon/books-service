package com.indra.ar.books.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.ar.books.conf.JmsBrokerConfig;
import com.indra.ar.books.domain.Email;
import com.indra.ar.books.domain.LibroDTO;
import com.indra.ar.books.domain.SearchBookCriteria;
import com.indra.ar.books.entities.Books;
import com.indra.ar.books.exceptions.BookNotFoundException;
import com.indra.ar.books.repositories.BooksDao;
import com.indra.ar.books.repositories.BooksRepository;

import lombok.Getter;

@Service
public class BooksCrudService extends AbstractService implements BooksService{

	@Autowired
	@Getter
	BooksRepository booksRepository;
	
	@Autowired
	@Getter
	BooksDao booksDao;
	
	@Override
	public LibroDTO save(LibroDTO element) {

		LibroDTO libroAdded = getTransformDtoToEntity()
				.booksToLibroDto(getBooksRepository().save(getTransformDtoToEntity().libroDtoToBooks(element)));

		getJmsTemplate().convertAndSend(JmsBrokerConfig.MAILBOX_QUEUE,Email.builder().body(element.getTitulo()).to(element.getAutor()).build());

		return libroAdded;
	}
	
	@Override
	public LibroDTO update(LibroDTO element) {
		
		getBooksRepository().findById(element.getId()).orElseThrow(() -> new BookNotFoundException("Book :"+element.getId().toString()+" Not found"));
		
		return getTransformDtoToEntity().booksToLibroDto(getBooksRepository().save(getTransformDtoToEntity().libroDtoToBooks(element)));	
	}
	
	@Override
	public void deleteById(Long id) {
		
		getBooksRepository().findById(id).orElseThrow(() -> new BookNotFoundException("Book :"+id.toString()+" Not found"));
		
		getBooksRepository().deleteById(id);
		
	}
	
	@Override
	public List<LibroDTO> findBookByTitulo(SearchBookCriteria searchBookCriteria){
		
		List<LibroDTO> result =new ArrayList<>();
		
		getBooksRepository().findBooksBytitulo(searchBookCriteria.getTitulo()).forEach(P -> result.add(getTransformDtoToEntity().booksToLibroDto(P)));
		
		return result;
	}

	@Override
	public List<LibroDTO> findBookByCriteria(SearchBookCriteria searchBookCriteria){
		
		List<LibroDTO> result =new ArrayList<>();

		getBooksDao().searchBooksByCriteria(searchBookCriteria).forEach(P -> result.add(getTransformDtoToEntity().booksToLibroDto(P)));
		
		return result;
	}
	
	@Override
	public LibroDTO findByid(Long id) {
		
		getLog().info("---BUSINESS LAYER --LOCAL PARAMETER--Llamada a servicio BooksCrudService-findById: Entrada a servicio con el parametro");
		
		
		getLog().info("---DAO LAYER --RETURN ENTITY--Llamada a servicio BooksRepository()-findById: Llamada a base de datos devuelve Entitie de Capa DAO a Capa Bussines");
		Optional<Books> result = getBooksRepository().findById(id);
		
		if(result.isPresent()) {
			getLog().info("---BUSINESS LAYER --TRANSFORM ENTITY To DTO --Transformacion de la entidad de base de datos a Dto y retorno de Bussines a Controller");
			return getTransformDtoToEntity().booksToLibroDto(result.get());
		}else {
			throw new BookNotFoundException("Book id "+id.toString());
		}
	}

}
