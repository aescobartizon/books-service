package com.indra.ar.books.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indra.ar.books.domain.LibroDTO;
import com.indra.ar.books.domain.LibroDTOImp;
import com.indra.ar.books.domain.SearchBookCriteria;
import com.indra.ar.books.services.BooksService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;

@RestController
@RequestMapping("/api/")
@Api(value = "Books", description = "Rest API for books operations", tags = "Books API")
public class BooksRestApi extends AbstractController{

	private static final String BOOKS_END_POINT = "/books";
	
	private static final String FIND_BOOKS_END_POINT = "/findbooks";
	
	private static final String FIND_BOOKS_BY_CRITERIA_END_POINT = "/findbooksbycriteria";
	
	
	@Autowired
	@Getter
	BooksService bookService;
	
	@GetMapping(path = BOOKS_END_POINT + "/{id}")
    @ApiOperation(value = "Display book info to non-admin user", response = LibroDTO.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
	public Resource<LibroDTO> getBook(@PathVariable Long id) {
			
		getLog().info("---CONTROLLER LAYER --REQUEST PARAMETER--Llamada a servicio Rest -- Entrada de Request");
		LibroDTO result =  getBookService().findByid(id);
		
		Resource<LibroDTO> resource = new Resource<>(result);
		
		getLog().info("---CONTROLLER LAYER --CREATE RESOURCE WITH DTO--Retorno de Resource con la informacion del DTO devuelto por la capa Bussines");
		return resource;

	}
	
	@GetMapping(path = FIND_BOOKS_BY_CRITERIA_END_POINT+ "/{isbn}" )
    @ApiOperation(value = "Display books info filtered by critera", response = LibroDTO.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
	public List<LibroDTO> getBooksByCriteria(@PathVariable String isbn) {
		
		return  getBookService().findBookByCriteria(SearchBookCriteria.builder().isbn(isbn).build());

	}
	
	
	@GetMapping(path = FIND_BOOKS_END_POINT + "/{titulo}")
    @ApiOperation(value = "Display books info filtered by title", response = LibroDTO.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
	public List<LibroDTO> getBooksByTitulo(@PathVariable String titulo) {
		
		return  getBookService().findBookByTitulo(SearchBookCriteria.builder().titulo(titulo).build());

	}
	
	@PostMapping(path = BOOKS_END_POINT)
	@ApiOperation(value = "Add book to stock", response = LibroDTO.class)
	public LibroDTO createBook(@Valid @RequestBody LibroDTOImp libro) {
		
		return  getBookService().save(libro);
	}
	
	@PutMapping(path = BOOKS_END_POINT)
	@ApiOperation(value = "update book info", response = LibroDTO.class)
	public LibroDTO updateBook(@Valid @RequestBody LibroDTOImp libro) {

		return getBookService().update(libro);
		
	}
	
	@DeleteMapping(path = BOOKS_END_POINT + "/{id}")
	@ApiOperation(value = "delete book from stock", response = LibroDTO.class)
	public void deleteBook(@PathVariable Long id) {
		
		getBookService().deleteById(id);
		
	}
}
