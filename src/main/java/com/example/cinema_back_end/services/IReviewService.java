package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.ReviewDTO;

/**
 * @author tritcse00526x
 */
public interface IReviewService extends IGeneralService<ReviewDTO>{
    ReviewDTO add(ReviewDTO review, Integer integer);
}
