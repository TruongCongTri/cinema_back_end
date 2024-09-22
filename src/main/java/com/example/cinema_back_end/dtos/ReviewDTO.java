package com.example.cinema_back_end.dtos;

import com.example.cinema_back_end.entities.User;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author tritcse00526x
 */
@Data
public class ReviewDTO {
    private Integer id;
    private float rating;
    private String comment;
    private LocalDate commentDate;

    private Integer movieId;
    private MovieDTO movie;
    private User user;
}
