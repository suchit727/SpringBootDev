package com.example.cartanditem.model;

import com.example.cartanditem.entity.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String name;
    private Long quantity;
    private Double price;

}
