package com.example.cartanditem.model;

import com.example.cartanditem.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String cartName;
    private List<Item> items;
}
