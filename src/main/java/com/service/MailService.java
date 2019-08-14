package com.service;

import com.model.Code;

public interface MailService {

    void sendConfirmCode(Code code, String email);
}
