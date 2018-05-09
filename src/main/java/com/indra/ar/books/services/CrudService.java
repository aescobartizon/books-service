package com.indra.ar.books.services;

public interface CrudService<T> {
	
	T save(T element);
	
	T update(T element); 
	
	T findByid(Long id);
	
	void deleteById(Long id); 

}
