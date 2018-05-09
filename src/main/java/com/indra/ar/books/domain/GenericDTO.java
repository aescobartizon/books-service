package com.indra.ar.books.domain;

import java.io.Serializable;

public interface GenericDTO extends Serializable  {

	<T> T deepClone();

}
