package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.PromotionDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IPromotionService extends IGeneralService<PromotionDTO> {
    List<PromotionDTO> findAllActivePromotions();
    PromotionDTO findActivePromotionById(Integer id);
}
