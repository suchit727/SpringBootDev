package com.manytomany.controller;

import com.manytomany.entity.BookEntity;
import com.manytomany.model.Author;
import com.manytomany.model.Book;
import com.manytomany.resposne.BookAuthorResponse;
import com.manytomany.service.BookAuthorService;
import com.manytomany.service.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class BookAuthorController {

    @Autowired
    private final BookAuthorService bookAuthorService;
    @Autowired
    private BookService bookService;


    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @PostMapping(path = "/addbooks" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<BookAuthorResponse> addBooks(@RequestBody Book book) {
        BookAuthorResponse bookAuthorResponse = bookAuthorService.addBooks(book);
        return new ResponseEntity<>(bookAuthorResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/books/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Book> getBooks(@PathVariable Long id) throws Exception {
        Book book = bookAuthorService.getBooks(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(path = "/books-authors/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Book> getBook(@PathVariable Long id) throws Exception {
        Book book = bookAuthorService.getBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Book>> getBooks() throws Exception{
        List<Book> allBooks=bookAuthorService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);

    }

    @PutMapping(path = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> updateBooks(@PathVariable Long id, @RequestBody Book book){
        bookAuthorService.updateBooks(id, book);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/author/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> updateAuthor(@PathVariable Long id, @RequestBody Author author){
        bookAuthorService.updateAuthorList(id, author);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/books/{id}")
    private ResponseEntity<String> deleteBook(@PathVariable Long id) throws Exception{
        bookAuthorService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }


}
