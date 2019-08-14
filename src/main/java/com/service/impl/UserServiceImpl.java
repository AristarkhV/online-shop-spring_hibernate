package com.service.impl;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUser(User deleteUser) {
        userDao.deleteUser(deleteUser);
    }

    @Override
    @Transactional(readOnly = false)
    public void editUser(User value) {
        userDao.editUser(value);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void addUser(User value) {
        userDao.addUser(value);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
