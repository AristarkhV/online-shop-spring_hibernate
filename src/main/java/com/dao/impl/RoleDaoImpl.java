package com.dao.impl;

import com.dao.RoleDao;
import com.model.Role;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
        logger.info(role + " is added to db");
    }

    @Override
    public Optional<Role> getRoleByName(String value) {
        Role role = sessionFactory.getCurrentSession().byNaturalId(Role.class)
                .using("name", value)
                .load();
        return Optional.ofNullable(role);
    }
}
