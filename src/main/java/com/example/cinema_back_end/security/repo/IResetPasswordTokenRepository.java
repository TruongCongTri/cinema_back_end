package com.example.cinema_back_end.security.repo;

import com.example.cinema_back_end.entities.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author tritcse00526x
 */
public interface IResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Integer> {
    ResetPasswordToken findByToken(String token);

    Optional<ResetPasswordToken> findByUserId(Integer userId);
}
