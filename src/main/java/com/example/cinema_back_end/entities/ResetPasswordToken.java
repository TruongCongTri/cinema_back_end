package com.example.cinema_back_end.entities;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tritcse00526x
 */
@Entity
@Data
@NoArgsConstructor
public class ResetPasswordToken {
    private static final int EXPIRATION_MINUTE = 60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Date expiryDate;

    public ResetPasswordToken(String token, User user) {
        this.token = token;
        this.user = user;
        //expire when current time plus 60 minutes
        this.expiryDate = new Date(Calendar.getInstance().getTime().getTime()
                + EXPIRATION_MINUTE * 60 * 1000);
    }
}
