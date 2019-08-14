package com.service;

import com.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    void addUser(User value);

    void deleteUser(User value);

    void editUser(User value);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);
}
