package com.dao.impl;

import com.dao.UserDao;
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
public class UserDaoImpl implements UserDao {

    private final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM User").list();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        logger.info(user + " is added to db");
    }

    @Override
    public void editUser(User value) {
        sessionFactory.getCurrentSession().update(value);
        logger.info(value + " is edited in db");
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(User.class, userId));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<User> query = session.createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((User) list.get(0));
        }
    }

    @Override
    public void deleteUser(User value) {
        try {
            sessionFactory.getCurrentSession().delete(value);
        } catch (Exception e){
            logger.info("User " + value + " isn't deleted. ", e);
        }
    }
}
