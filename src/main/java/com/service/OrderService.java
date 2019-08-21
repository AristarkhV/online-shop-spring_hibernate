package com.service;

import com.model.Code;
import com.model.Order;
import com.model.Product;
import com.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void addOrder(Order value);

    Optional<Order> getUserOrder(User value);

    void createOrder(User user, String email, String deliveryAddress, List<Product> products);

    Code sendConfirmationCode(String email, Order order);
}
