package com.crossasyst.onetoone.repository;

import com.crossasyst.onetoone.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
}
