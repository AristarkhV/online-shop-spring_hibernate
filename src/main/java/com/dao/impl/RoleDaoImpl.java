package com.dao.impl;

import com.dao.RoleDao;
import com.model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
        logger.info(role + " is added to db");
    }

    @Override
    public Optional<Role> getRoleByName(String value) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Role> query = session.createQuery("FROM Role WHERE role = :name");
        query.setParameter("name", value);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((Role) list.get(0));
        }
    }
}
