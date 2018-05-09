package com.indra.ar.books.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	private String to;
	private String body;

	public Email() {
	}

	public Email(String to, String body) {
		this.to = to;
		this.body = body;
	}

	@Override
	public String toString() {
		return String.format("Email{to=%s, body=%s}", getTo(), getBody());
	}

}
