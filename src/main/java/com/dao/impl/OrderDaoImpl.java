package com.dao.impl;

import com.dao.OrderDao;
import com.model.Order;
import com.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrder(Order value) {
        sessionFactory.getCurrentSession().save(value);
        logger.info(value + " is added to db");
    }

    @Override
    public Optional<Order> getUserOrder(User value) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Order> query = session.createQuery("FROM Order WHERE user = :user ORDER BY idOrder DESC");
        query.setParameter("user", value);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((Order) list.get(0));
        }
    }
}
