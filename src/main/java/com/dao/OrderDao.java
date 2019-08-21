package com.dao;

import com.model.Order;
import com.model.User;

import java.util.Optional;

public interface OrderDao {

    void addOrder(Order value);

    Optional<Order> getUserOrder(User value);
}
