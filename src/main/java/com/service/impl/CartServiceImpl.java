package com.service.impl;

import com.dao.CartDao;
import com.model.Cart;
import com.model.Product;
import com.model.User;
import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private CartDao cartDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void addCart(Cart value) {
        cartDao.addCart(value);
    }

    @Override
    public Optional<Cart> getCart(User value) {
        return cartDao.getCart(value);
    }

    @Override
    @Transactional(readOnly = false)
    public void addProductToCart(Cart cart, Product product) {
        cartDao.addProductToCart(cart, product);
    }

    @Override
    public int cartSize(User value) {
        return cartDao.getCart(value).isPresent()
                ? cartDao.getCart(value).get().getProducts().size()
                : 0;
    }

    @Override
    @Transactional(readOnly = false)
    public void cleanCart(Cart value) {
        cartDao.cleanCart(value);
    }

}
