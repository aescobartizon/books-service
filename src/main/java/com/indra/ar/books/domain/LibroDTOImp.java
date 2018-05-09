package com.indra.ar.books.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LibroDTOImp extends AbstractDTO implements LibroDTO {

	private static final long serialVersionUID = 1143490730364007930L;
	
	public LibroDTOImp() {
		super();
		this.titulo= "";
	}
	
	public LibroDTOImp(Long id, String titulo, String autor, Long fechaPublicacion, String isbn) {
		super();
		this.id = id;
		this.titulo = titulo != null ? titulo : "";
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.isbn = isbn;
	}
	
	
	@ApiModelProperty(notes = "internal Book id", required =false)
	private Long id;

	@Size(min=2, message = "Name should have atleast 2 characters")
	@ApiModelProperty(notes = "Lenght sould be unless 1",required =true)
	@JsonProperty("titulo")
    @NotNull
	private String titulo;

	
	@ApiModelProperty(notes = "author Book ", required =true)
	private String autor;

	
	@ApiModelProperty(notes = "fecha Publicacion id", required =true)
	private Long fechaPublicacion;

	
	@ApiModelProperty(notes = "ISBN Book ", required =false)
	private String isbn;

}
