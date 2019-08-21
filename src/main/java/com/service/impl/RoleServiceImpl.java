package com.service.impl;

import com.dao.RoleDao;
import com.model.Role;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Optional<Role> getRoleByName(String value) {
        return roleDao.getRoleByName(value);
    }
}
