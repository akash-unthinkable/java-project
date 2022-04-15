package com.example.demo.api.controllers;

import com.example.demo.api.entity.Book;
import com.example.demo.api.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {

        List<Book> list= bookService.getAllbooks();
        if(list.size()<=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/books/{page}")
    public ResponseEntity<List<Book>> getAllSortedBooks(@PathVariable("page") int page) {

        List<Book> list= bookService.findAllSortingDesc(page);
        if(list.size()<=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getbook(@PathVariable("id") int id ){

        Optional <Book>book=bookService.getBookById(id);
        if (!book.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(book);
    }
    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
      Book b= null;
      try {
          b = this.bookService.addBook(book);
          return ResponseEntity.of(Optional.of(b));
      }catch (Exception e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }
    // delete book
    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId){
        try {
            this.bookService.deleteBook(bookId);
          return   ResponseEntity.ok().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }
    //update book
    @PutMapping("/book/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
        try {
            this.bookService.updateBook(book, bookId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
