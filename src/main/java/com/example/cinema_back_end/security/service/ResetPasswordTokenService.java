package com.example.cinema_back_end.security.service;

import com.example.cinema_back_end.entities.ResetPasswordToken;
import com.example.cinema_back_end.entities.User;
import com.example.cinema_back_end.security.repo.IResetPasswordTokenRepository;
import com.example.cinema_back_end.security.repo.IUserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

/**
 * @author tritcse00526x
 */
@Service
public class ResetPasswordTokenService implements IResetPasswordTokenService{
    @Autowired
    private IResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    private IUserRepository userRepository;

/*START-Override from IResetPasswordTokenService*/
    /**TODO: FORGET PASSWORD page*/
    /*START - FORGET PASSWORD page*/
    @Override
    public ResetPasswordToken generateResetPasswordToken(String email) throws AccountNotFoundException {
        User user = userRepository.findByUsername(email).orElse(null);
        if (user != null) { // if email had been registered
            String token = RandomString.make(30);
            ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByUserId(user.getId()).orElse(null);
            if(resetPasswordToken == null) {
                resetPasswordToken = new ResetPasswordToken(token, user);
            } else {
                Integer id = resetPasswordToken.getId();
                resetPasswordToken = new ResetPasswordToken(token, user);
                resetPasswordToken.setId(id);
            }
            return resetPasswordTokenRepository.save(resetPasswordToken);
        } else { // if email hasn't been registered
            // then throw exception
            throw new AccountNotFoundException("Email không tồn tại");
        }
    }

    @Override
    public ResetPasswordToken getByToken(String token) {
        return resetPasswordTokenRepository.findByToken(token);
    }

    @Override
    public void remove(Integer id) {
        resetPasswordTokenRepository.deleteById(id);
    }
/*END-Override from IResetPasswordTokenService*/
}
