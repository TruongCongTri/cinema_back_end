package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.dtos.PromotionDTO;
import com.example.cinema_back_end.services.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/promotions", produces = "application/json")
public class PromotionAPI {
    @Autowired
    private IPromotionService promotionService;

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> findAll() {
        System.out.println("LOG: Get all promotions");
        return new ResponseEntity<>(promotionService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/active")
    public ResponseEntity<List<PromotionDTO>> findActivePromotion() {
        System.out.println("LOG: Get all active promotions");
        return new ResponseEntity<>(promotionService.findAllActivePromotions(),HttpStatus.OK);
    }

    /*@GetMapping
    public ResponseEntity<List<PromotionDTO>> findAll() {
        return new ResponseEntity<>(promotionService.findAll(),HttpStatus.OK);
    }*/

    @GetMapping("/active/detail")
    public PromotionDTO findActivePromotion(@RequestParam Integer promoId) {
        System.out.println("LOG: Get promotion detail");
        return promotionService.findActivePromotionById(promoId);
    }

}