package com.polarbookshop.catalogservice.demo;


import java.util.List;
import java.util.Random;

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
    String rIsbn = generateISBN();
    var book1 = Book.of(rIsbn, "title1", "author1", 9.99, "Book Cat");
    rIsbn = generateISBN();
    var book2 = Book.of(rIsbn, "동조자1", "비엣 타인 응우옌", 14.10, "Book Cat");
    try {
      bookRepository.saveAll(List.of(book1, book2));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    
    
  }
  
  private String generateISBN() {
    Random r = new Random();
    int createNum = 0;
    String ranNum ="";
    int letter = 10;
    String resultNum = "";
    
    for(int i=0; i<letter; i++) {
      createNum = r.nextInt(9);
      ranNum = Integer.toString(createNum);
      
      resultNum += ranNum;
    }
    
    return resultNum;
  }
}
