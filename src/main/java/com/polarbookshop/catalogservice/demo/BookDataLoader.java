package com.polarbookshop.catalogservice.demo;


import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookReposiotry;

@Component
@Profile("testdata")
@ConditionalOnProperty(name = "polar.testdata.enabled", havingValue = "true")
public class BookDataLoader {

  private final BookReposiotry bookRepository;
  
  public BookDataLoader(BookReposiotry bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  @EventListener(ApplicationReadyEvent.class)
  public void loadBookTestData() {
    System.out.println("+++++++++++loadBookTestData+++++++++++++++");
    bookRepository.deleteAll();
    
    var book1 = Book.of("1234567891", "title1", "author1", 9.99);
    var book2 = Book.of("2345678901", "동조자1", "비엣 타인 응우옌", 14.10);
    try {
      bookRepository.saveAll(List.of(book1, book2));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    
    
  }
}
