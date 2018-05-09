package com.indra.ar.books.exceptions;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponse {

	private Date timestamp;
	
	private String message;
	
	private String details;
}
