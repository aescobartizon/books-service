package com.indra.ar.books.domain;

import java.io.Serializable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AbstractDTO  implements Serializable, GenericDTO {

	private static final long serialVersionUID = -7577381570382865330L;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
	}

}
