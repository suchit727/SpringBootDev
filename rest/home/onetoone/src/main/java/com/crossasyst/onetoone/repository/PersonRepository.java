package com.crossasyst.onetoone.repository;

import com.crossasyst.onetoone.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
}
