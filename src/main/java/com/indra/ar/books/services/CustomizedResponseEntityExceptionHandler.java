package com.indra.ar.books.services;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.indra.ar.books.exceptions.BookNotFoundException;
import com.indra.ar.books.exceptions.ExceptionResponse;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse =  ExceptionResponse.builder().timestamp(new Date()).message(ex.getMessage()).details(request.getDescription(false)).build();

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(new Date()).message(ex.getMessage()).details(request.getDescription(false)).build();

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
}
