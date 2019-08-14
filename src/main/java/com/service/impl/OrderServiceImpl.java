package com.service.impl;

import com.dao.CodeDao;
import com.dao.OrderDao;
import com.model.Code;
import com.model.Order;
import com.model.Product;
import com.model.User;
import com.service.MailService;
import com.service.OrderService;
import com.util.RandomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    private CodeDao codeDao;

    private MailService mailService;

    @Override
    @Transactional(readOnly = false)
    public Code sendConfirmationCode(String email, Order order) {
        String confirmationCode = RandomHelper.generateCode();
        Code code = new Code(confirmationCode, order, email);
        codeDao.addCode(code);
        mailService.sendConfirmCode(code, email);
        return code;
    }

    @Override
    @Transactional(readOnly = false)
    public void createOrder(User user, String email, String deliveryAddress, List<Product> products) {
        Order userOrder = new Order(user, email, deliveryAddress, products);
        orderDao.addOrder(userOrder);
    }

    @Override
    public Optional<Order> getUserOrder(User value) {
        return orderDao.getUserOrder(value);
    }

    @Override
    @Transactional(readOnly = false)
    public void addOrder(Order value) {
        orderDao.addOrder(value);
    }
}
