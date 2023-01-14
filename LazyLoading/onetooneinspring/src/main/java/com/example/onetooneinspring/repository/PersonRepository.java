package com.example.onetooneinspring.repository;

import com.example.onetooneinspring.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
}
