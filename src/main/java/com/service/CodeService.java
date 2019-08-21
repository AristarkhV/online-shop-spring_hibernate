package com.service;

import com.model.Code;

import java.util.Optional;

public interface CodeService {

    void addCode(Code value);

    Optional<Code> getCode(String email);
}
