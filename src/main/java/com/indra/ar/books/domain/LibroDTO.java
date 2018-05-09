package com.indra.ar.books.domain;

public interface LibroDTO extends GenericDTO{
	
	Long getId();
	
	void setId(Long id);

	String getTitulo();
	
	void setTitulo(String titulo);
	
	String getAutor();
	
	void setAutor(String autor);
	
	Long getFechaPublicacion();
	
	void setFechaPublicacion(Long fechaPublicacion);
	
	String getIsbn();
	
	void setIsbn(String isbn);
}
