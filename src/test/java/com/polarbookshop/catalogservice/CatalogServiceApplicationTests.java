package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.polarbookshop.catalogservice.domain.Book;


@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT

)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

  @Autowired
  private WebTestClient webTestClient;
  
  
  @Test
  void whenPostRequestThenBookCreated() {
    var expectedBook =Book.of("1234567890", "title2", "Author2", 9.99);
    
    webTestClient
      .post()
      .uri("/books")
      .bodyValue(expectedBook)
      .exchange()
      .expectStatus().isCreated()
      .expectBody(Book.class).value(actualBook -> {
        assertThat(actualBook).isNotNull();
        assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
      });
    
  }
  
  
	@Test
	void contextLoads() {
    
	  
	}

}


