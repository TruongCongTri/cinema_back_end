package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.PromotionDTO;
import com.example.cinema_back_end.entities.Promotion;
import com.example.cinema_back_end.repositories.IPromotionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class PromotionService implements IPromotionService{
    @Autowired
    private IPromotionRepository promotionRepository;
    @Autowired
    private ModelMapper modelMapper;

/*START-Override from IPromotionService*/
    /**TODO: PROMOTION page*/
    /*START - PROMOTION page*/
    // get all active promotions
    @Override
    public List<PromotionDTO> findAllActivePromotions() {
        return promotionRepository.findPromotionsByIsActive(1)
                .stream()
                .map((promotion) -> modelMapper.map(promotion, PromotionDTO.class))
                .collect(Collectors.toList());
    }
    // get detail of active promotion
    @Override
    public PromotionDTO findActivePromotionById(Integer id) {
        return modelMapper.map(promotionRepository.findPromotionByIdAndIsActive(id,1), PromotionDTO.class);
    }
    /*END - PROMOTION page*/
/*END-Override from IPromotionService*/

/*START-Override from IGeneralService*/
    @Override
    public List<PromotionDTO> findAll() {
        return promotionRepository.findAll()
                .stream()
                .map((promotion) -> modelMapper.map(promotion, PromotionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PromotionDTO getById(Integer id) { return modelMapper.map(promotionRepository.getById(id), PromotionDTO.class);}

    @Override
    public void update(PromotionDTO promotion) { promotionRepository.save(modelMapper.map(promotion, Promotion.class));}

    @Override
    public void remove(Integer id) {
        promotionRepository.deleteById(id);
    }

/*END-Override from IGeneralService*/
}
