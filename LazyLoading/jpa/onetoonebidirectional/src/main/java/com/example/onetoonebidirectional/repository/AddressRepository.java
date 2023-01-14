package com.example.onetoonebidirectional.repository;

import com.example.onetoonebidirectional.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface AddressRepository extends JpaRepository<AddressEntity,Long> {
}
