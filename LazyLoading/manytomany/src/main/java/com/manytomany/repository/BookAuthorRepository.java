package com.manytomany.repository;

import com.manytomany.dto.BookDto;
import com.manytomany.entity.BookEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookEntity, Long> {
    @Query("Select b From BookEntity b Left Join Fetch b.authorList where b.id=?1")
    BookEntity findByIdQuery(Long id);

    @EntityGraph(attributePaths = "authorList")
    BookEntity findAuthorById(Long id);
   /* @Query("Select new com.manytomany.dto.BookDto(b.bookName) from BookEntity b where b.id=?1")
    BookDto findByBookId(Long id);*/
    @Query("Select new com.manytomany.dto.BookDto(b.bookName,b.authorList) from BookEntity b left Join b.authorList ba where b.id=?1")
    BookDto findByBookId(Long id);
}
