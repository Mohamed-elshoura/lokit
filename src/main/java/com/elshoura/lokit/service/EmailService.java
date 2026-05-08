package com.elshoura.lokit.service;

public interface EmailService {

    void sendPasswordResetCode(String to, String code);

}
