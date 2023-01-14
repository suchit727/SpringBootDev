package com.example.cartanditem.controller;

import com.example.cartanditem.model.Cart;
import com.example.cartanditem.model.CartResponse;
import com.example.cartanditem.model.Item;
import com.example.cartanditem.service.CartAndItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class CartAndItemController {

    @Autowired
    private CartAndItemService cartAndItemService;
    @PostMapping(path = "/carts", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> createItems(@RequestBody Cart cart) {
        CartResponse cartResponse = cartAndItemService.createItems(cart);
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }
    @GetMapping(path = "/carts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Cart cart = cartAndItemService.getCart(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
  /*  @PutMapping(path = "/carts/{id}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        cart = cartAndItemService.updateCart(id, cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }*/
    @DeleteMapping(path = "/carts/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        cartAndItemService.deleteCart(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping(path = "/carts/{id}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCart(@PathVariable Long id, @RequestBody Item item, @RequestParam String name)  {
        cartAndItemService.updateCart(id,item,name);
        return ResponseEntity.ok().build();
    }

}
