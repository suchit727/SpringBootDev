package com.example.onetooneinspring.repository;

import com.example.onetooneinspring.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
}
