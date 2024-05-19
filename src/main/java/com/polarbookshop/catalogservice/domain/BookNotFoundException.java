package com.polarbookshop.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String isbn) {
		super(String.format("The book with ISBN %s was not found.", isbn));
	}
 }
