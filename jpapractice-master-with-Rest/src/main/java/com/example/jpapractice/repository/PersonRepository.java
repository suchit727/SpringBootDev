package com.example.jpapractice.repository;

import com.example.jpapractice.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends  JpaRepository<PersonEntity, Long> {

/*    PersonEntity findByFirstName(String firstName);*/
}
