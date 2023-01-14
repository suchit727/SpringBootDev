package com.manytomany.service;

import com.manytomany.dto.BookDto;
import com.manytomany.entity.BookEntity;
import com.manytomany.mapper.AuthorMapper;
import com.manytomany.mapper.BookAuthorMapper;
import com.manytomany.model.Book;
import com.manytomany.repository.AuthorRepositroy;
import com.manytomany.repository.BookAuthorRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private  BookAuthorRepository bookAuthorRepository;

    @Autowired
    private  AuthorRepositroy authorRepositroy;
   @Autowired
   private BookAuthorMapper mapper;



    @Cacheable(cacheNames = "book",key = "#id")
    public Book getBookById(Long id) {
        BookEntity bookEntity=bookAuthorRepository.findAuthorById(id);
        Book book =mapper.entityToModel(bookEntity);
        return book;
    }

    public BookDto getBook(Long id) {
        BookDto bookDto=bookAuthorRepository.findByBookId(id);
        return bookDto;
    }
}
