
package com.example.cinema_back_end.security.service;

import com.example.cinema_back_end.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

/**
 * @author tritcse00526x
 */
//after login, spring security get information from UserDetails
public interface IUserService extends IGeneralService<User>, UserDetailsService { //a class dedicated to UserDetails information checking
    Optional<User> findByUsername(String username);
    void updateInfo(Integer userId, User user);
    void changePassword(Integer id,String newPass);
    void saveUserByAdmin(User user, boolean b) throws Exception;
    public User getByResetPasswordToken(String token);
}

