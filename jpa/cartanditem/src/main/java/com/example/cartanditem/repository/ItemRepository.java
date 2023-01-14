package com.example.cartanditem.repository;

import com.example.cartanditem.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
    ItemEntity findByName(String name);
}
