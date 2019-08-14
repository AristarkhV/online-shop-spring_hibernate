package com.dao;

import com.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAll();

    void addUser(User value);

    void deleteUser(User value);

    void editUser(User value);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserById(Long id);
}
