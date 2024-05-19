package com.polarbookshop.catalogservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

  /*
   * 모의 환경에서 웹 계층을 테스트하기 위한 유틸리티 클래스
   * */
  @Autowired
  private MockMvc mockMvc;
  
  
  @MockBean
  private BookService bookService;
  
  
  @Test
  void whenGetBookNotExistingThenShouldReturn404() throws Exception {
    var isbn = "11122221111";
    
    given(bookService.viewBookDetails(isbn))
      .willThrow(BookNotFoundException.class);
    
    mockMvc
      .perform(get("/books/"+isbn))
      .andExpect(status().isNotFound());
    
  }
}
