package com.service;

import com.model.Cart;
import com.model.Product;
import com.model.User;

import java.util.Optional;

public interface CartService {

    void addCart(Cart cart);

    Optional<Cart> getCart(User value);

    void addProductToCart(Cart cart, Product product);

    int cartSize(User value);

    void cleanCart(Cart cart);
}
