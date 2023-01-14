package com.manytomany.service;

import com.manytomany.entity.AuthorEntity;
import com.manytomany.entity.BookEntity;
import com.manytomany.mapper.BookAuthorMapper;
import com.manytomany.model.Author;
import com.manytomany.model.Book;
import com.manytomany.repository.AuthorRepositroy;
import com.manytomany.repository.BookAuthorRepository;
import com.manytomany.resposne.BookAuthorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BookAuthorService {
    private final BookAuthorMapper bookAuthorMapper;
    private final BookAuthorRepository bookAuthorRepository;
    private final AuthorRepositroy authorRepositroy;

    public BookAuthorService(BookAuthorMapper bookAuthorMapper, BookAuthorRepository bookAuthorRepository, AuthorRepositroy authorRepositroy) {
        this.bookAuthorMapper = bookAuthorMapper;
        this.bookAuthorRepository = bookAuthorRepository;
        this.authorRepositroy = authorRepositroy;
    }

    public BookAuthorResponse addBooks(Book book) {
        new BookEntity();
        BookEntity bookEntity;
        bookEntity = bookAuthorMapper.modelTOEntity(book);
        bookAuthorRepository.save(bookEntity);
        log.info("book details saved in database");
        BookAuthorResponse bookAuthorResponse = new BookAuthorResponse();
        bookAuthorResponse.setId(bookEntity.getId());
        return bookAuthorResponse;
    }


    public Book getBooks(Long id) throws Exception {
        Optional<BookEntity> bookEntityOptional = bookAuthorRepository.findById(id);
        Book book = new Book();
        if (bookEntityOptional.isPresent()) {
            BookEntity bookEntity = bookEntityOptional.get();
         book = bookAuthorMapper.entityToModel(bookEntity);
            log.info("Book with the id {} found", id);
        } else {
            log.info("No book found with the id {}", id);
        }
        return book;
    }

    public Book getBook(Long id) throws Exception {
        BookEntity bookEntity = bookAuthorRepository.findByIdQuery(id);
        Book book = bookAuthorMapper.entityToModel(bookEntity);
        log.info("Book with the id {} found", id);
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> allBooks = bookAuthorMapper.bookEntityToModel(bookAuthorRepository.findAll());
        log.info("Getting list of books !!");
        return allBooks;

    }

    public void updateBooks(Long id, Book book) {
        Optional<BookEntity> optionalBookEntity = bookAuthorRepository.findById(id);

        if (optionalBookEntity.isPresent()) {
            optionalBookEntity.get().setBookName(book.getBookName());
            bookAuthorRepository.save(optionalBookEntity.get());
            log.info("Book with id {} updated successfully", id);
        } else {
            log.info("Book not found with id {},try again", id);
        }
    }


    public void updateAuthorList(Long id, Author author) {
        Optional<AuthorEntity> optionalAuthorEntity = authorRepositroy.findById(id);
        if (optionalAuthorEntity.isPresent()) {
            optionalAuthorEntity.get().setAuthorName(author.getAuthorName());
            log.info("Author names with id {} updated successfully", id);
        } else {
            log.info("Author not found with id {},try again", id);
        }
    }

    public void deleteBook(Long id) {
        bookAuthorRepository.deleteById(id);
        log.info("Book with id {} deleted successfully", id);
    }



}


