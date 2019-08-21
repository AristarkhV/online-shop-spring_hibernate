package com.dao.impl;

import com.dao.CartDao;
import com.model.Cart;
import com.model.Product;
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
public class CartDaoImpl implements CartDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public CartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCart(Cart value) {
        sessionFactory.getCurrentSession().save(value);
        logger.info(value + " is added to db");
    }

    @Override
    public Optional<Cart> getCart(User value) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Cart> query = session.createQuery("FROM Cart WHERE user = :user ORDER BY idCart DESC");
        query.setParameter("user", value);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((Cart) list.get(0));
        }
    }

    @Override
    public void addProductToCart(Cart cart, Product product) {
        Session session = sessionFactory.getCurrentSession();
        cart.getProducts().add(product);
        session.update(cart);
        logger.info(product + " is added to " + cart);
    }

    @Override
    public void cleanCart(Cart value) {
        Session session = sessionFactory.getCurrentSession();
        value.getProducts().clear();
        session.update(value);
        logger.info(value + " is empty");
    }
}
