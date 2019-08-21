package com.dao;

import com.model.Cart;
import com.model.Product;
import com.model.User;

import java.util.Optional;

public interface CartDao {

    void addCart(Cart cart);

    Optional<Cart> getCart(User value);

    void addProductToCart(Cart cart, Product product);

    void cleanCart(Cart cart);
}
