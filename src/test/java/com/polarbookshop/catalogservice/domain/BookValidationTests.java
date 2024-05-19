package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BookValidationTests {

	private static Validator validator;
	
	@BeforeAll
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		
		validator = factory.getValidator();
	}
	
	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		var book = new Book("1234567800", "title", "Author", 9.99);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).isEmpty();
		
	}
	
	@Test
	void whenIsbnDefineButIncorrectThenValidationFails() {
		var book = new Book("12345a67800", "title", "Author", 9.99);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
				.isEqualTo("The ISBN format must be valid.");
	}
}
