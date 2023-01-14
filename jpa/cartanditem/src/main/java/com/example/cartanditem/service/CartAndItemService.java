package com.example.cartanditem.service;


import com.example.cartanditem.entity.CartEntity;
import com.example.cartanditem.entity.ItemEntity;
import com.example.cartanditem.mapper.CartMapper;
import com.example.cartanditem.model.Cart;
import com.example.cartanditem.model.CartResponse;
import com.example.cartanditem.model.Item;
import com.example.cartanditem.repository.CartRepository;
import com.example.cartanditem.repository.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CartAndItemService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private  CartMapper cartMapper;
    public CartResponse createItems(Cart cart) {
        CartResponse cartResponse=new CartResponse();
        CartEntity cartEntity=cartMapper.modelToEntity(cart);
        cartEntity.setCartName(cart.getCartName());
        List<Item> itemEntity=cart.getItems();
        cartRepository.save(cartEntity);
        log.info("Item has been added successfully in your cart");
        cartResponse.setId(cartEntity.getCartId());
        return  cartResponse;
    }

    public Cart getCart(Long id) {
        Optional<CartEntity> optionalCartEntity=cartRepository.findById(id);
        Cart cart =new Cart();
        if (optionalCartEntity.isPresent()){
            cart = cartMapper.entityToModel(optionalCartEntity.get());
            log.info("Received the item {} with " + id);
        }else {
            log.info("No items with id {} found " + id);
        }
        return cart;
    }
    public Cart updateCart(Long id, Cart cart) {
        CartEntity cartEntity = new CartEntity();
        Optional<CartEntity> optionalCartEntity = cartRepository.findById(id);
        if (optionalCartEntity.isPresent()) {
            cartEntity = cartMapper.modelToEntity(cart);
            cartRepository.save(cartEntity);
            log.info("Items has been updated");

        } else {
            log.info("No items with id {} found " + id);
        }
        return cart;
    }
    public void updateCart(Long id, Item item, String name) {

        ItemEntity itemEntity = itemRepository.findByName(name);
        if (itemEntity.getCartEntity().getCartId() == id && itemEntity.getName().equals(name)) {
            itemEntity.setQuantity(item.getQuantity());
            itemEntity.setPrice(item.getPrice());
            itemRepository.save(itemEntity);
        }
    }

    public void deleteCart(Long id) {
        Optional<CartEntity> optionalCartEntity = cartRepository.findById(id);
        if (optionalCartEntity.isPresent()) {
            cartRepository.deleteById(id);
            log.info("Item has been deleted");
        } else {
            log.info("No items with id {} found " + id);
        }
    }
}
