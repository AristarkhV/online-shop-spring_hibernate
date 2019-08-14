package com.dao.impl;

import com.dao.ProductDao;
import com.model.Product;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(Product value) {
        sessionFactory.getCurrentSession().save(value);
        logger.info(value + " is added to db");
    }

    @Override
    public void deleteProduct(Product value) {
        try {
            sessionFactory.getCurrentSession().delete(value);
        } catch (Exception e) {
            logger.info(value + " isn't deleted. ", e);
        }
    }

    @Override
    public void editProduct(Product value) {
        sessionFactory.getCurrentSession().update(value);
        logger.info(value + " is edited in db");
    }

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Product").list();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Product.class, id));
    }
}