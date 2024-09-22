package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tritcse00526x
 */
public interface IReviewRepository extends JpaRepository<Review, Integer> {
}
