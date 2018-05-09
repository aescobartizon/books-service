package com.indra.ar.books.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.indra.ar.books.controllers.AbstractController;

import lombok.Getter;

public class AbstractService {

	@Autowired
	@Getter
	private TransformDtoToEntity transformDtoToEntity;
	
	
	@Getter
	private static Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@Autowired
	@Getter
	JmsTemplate jmsTemplate; 
	
}
