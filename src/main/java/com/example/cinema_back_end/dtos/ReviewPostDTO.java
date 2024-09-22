package com.example.cinema_back_end.dtos;

import lombok.Data;

/**
 * @author tritcse00526x
 */
@Data
public class ReviewPostDTO {
    private float rating;
    private String comment;
    private Integer movieId;
}
