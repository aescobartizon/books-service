package com.indra.ar.books.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
public class Books implements Serializable{

	private static final long serialVersionUID = -8080804788892186024L;
	
	protected Books() {
		super();
	}

	public Books(Long id, String titulo, String autor, Long fechaPublicacion, String isbn) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.isbn = isbn;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "TITULO", updatable = true, nullable = false)
	private String titulo;

	@Column(name = "AUTOR", updatable = true, nullable = false)
	private String autor;

	@Column(name = "FECHAPUBLICACION", updatable = true, nullable = true)
	private Long fechaPublicacion;

	@Column(name = "ISBN", updatable = true, nullable = true)
	private String isbn;
	
}
