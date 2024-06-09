package com.polarbookshop.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.polarbookshop.catalogservice.domain.Book;

@JsonTest
public class BookJsonTests {

  @Autowired
  private JacksonTester<Book> json;
  
  @Test
  void testSerialize() throws IOException {
    var book = Book.of("1234567899", "title","author", 9.90, "Book Cat");
    var jsonContent = json.write(book);
    
    assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
      .isEqualTo(book.isbn());
    
    assertThat(jsonContent).extractingJsonPathStringValue("@.title")
    .isEqualTo(book.title());
  
    assertThat(jsonContent).extractingJsonPathStringValue("@.author")
    .isEqualTo(book.author());
  
    assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
    .isEqualTo(book.price());
  
  }
  
  @Test
  void testDeserialize() throws Exception {
    var content = """
        {
          "isbn" : "1234567899",
          "title": "title",
          "author":"author",
          "price": 9.90
        }
        """;
    assertThat(json.parse(content))
      .usingRecursiveComparison()
      .isEqualTo(Book.of("1234567899", "title","author", 9.90, "Book Cat"));
  }
}
