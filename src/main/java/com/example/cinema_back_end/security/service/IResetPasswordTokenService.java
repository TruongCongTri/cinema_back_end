package com.example.cinema_back_end.security.service;

import com.example.cinema_back_end.entities.ResetPasswordToken;

import javax.security.auth.login.AccountNotFoundException;

/**
 * @author tritcse00526x
 */
public interface IResetPasswordTokenService {
    public ResetPasswordToken generateResetPasswordToken(String email) throws AccountNotFoundException;
    public ResetPasswordToken getByToken(String token);
    public void remove(Integer id);
}
