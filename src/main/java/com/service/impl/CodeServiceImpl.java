package com.service.impl;

import com.dao.CodeDao;
import com.model.Code;
import com.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CodeServiceImpl implements CodeService {

    private CodeDao codeDao;

    @Autowired
    public CodeServiceImpl(CodeDao codeDao) {
        this.codeDao = codeDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void addCode(Code value) {
        codeDao.addCode(value);
    }

    @Override
    public Optional<Code> getCode(String email) {
        return codeDao.getCode(email);
    }
}
