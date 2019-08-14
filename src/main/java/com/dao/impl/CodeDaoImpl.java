package com.dao.impl;

import com.dao.CodeDao;
import com.model.Code;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CodeDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public CodeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCode(Code value) {
        sessionFactory.getCurrentSession().save(value);
        logger.info(value + " is added to db");
    }

    @Override
    public Optional<Code> getCode(String email) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Code> query = session.createQuery("FROM Code WHERE email = :email ORDER BY idCode DESC ");
        query.setParameter("email", email);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((Code) list.get(0));
        }
    }
}
