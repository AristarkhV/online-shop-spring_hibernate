package com.dao;

import com.model.Role;

import java.util.Optional;

public interface RoleDao {

    void addRole(Role role);

    Optional<Role> getRoleByName(String value);
}
