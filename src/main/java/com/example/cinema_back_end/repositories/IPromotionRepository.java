package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IPromotionRepository extends JpaRepository<Promotion, Integer> {
    //findAll
    //getById

    List<Promotion> findPromotionsByIsActive(int isActive);
    Promotion findPromotionByIdAndIsActive(int promotionId, int isActive);
}
