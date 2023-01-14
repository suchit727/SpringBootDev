package com.example.cartanditem.mapper;

import com.example.cartanditem.entity.CartEntity;
import com.example.cartanditem.entity.ItemEntity;
import com.example.cartanditem.model.Cart;
import com.example.cartanditem.model.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartEntity modelToEntity(Cart cartRequest);

    Cart entityToModel(CartEntity cartEntity);

    List<Cart> entityToModels(List<CartEntity> cartEntities);

    ItemEntity modelToEntityForItem(Item item);
    Item entityToModelForItem(ItemEntity cartEntity);
}
