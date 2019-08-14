package com.dao;

import com.model.Code;

import java.util.Optional;

public interface CodeDao {

    void addCode(Code value);

    Optional<Code> getCode(String email);
}
