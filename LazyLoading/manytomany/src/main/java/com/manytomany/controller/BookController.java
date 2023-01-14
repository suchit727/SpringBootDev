package com.manytomany.controller;

import com.manytomany.dto.BookDto;

import com.manytomany.model.Book;
import com.manytomany.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(path="/entity-book/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book=bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @GetMapping(path = "/trial-bookDtos/{id}")
    private ResponseEntity<BookDto> getBook(@PathVariable Long id){
        BookDto book=bookService.getBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
