package com.example.springmanytoone.repository;

import com.example.springmanytoone.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Long>{
}
