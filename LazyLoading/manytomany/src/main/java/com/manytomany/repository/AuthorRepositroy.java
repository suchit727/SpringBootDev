package com.manytomany.repository;

import com.manytomany.entity.AuthorEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepositroy extends JpaRepository<AuthorEntity, Long> {
    @EntityGraph(attributePaths = "bookEntities")
    AuthorEntity findAuthorById(Long id);
}
