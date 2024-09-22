package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.PromotionDTO;
import com.example.cinema_back_end.services.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/admin/promotions")
public class ManagePromotionAPI {
    @Autowired
    private IPromotionService promotionService;

    /**TODO: ADMIN - manage PROMOTION page*/
    @GetMapping
    public ResponseEntity<List<PromotionDTO>> findAllPromotions() {
        System.out.println("LOG: get all promotions");
        return new ResponseEntity<>(promotionService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/detail")
    public PromotionDTO getPromotionDetail(@RequestParam Integer promotionId){
        System.out.println("LOG: get promotion detail");
        return promotionService.getById(promotionId);
    }

    @PostMapping
    public ResponseEntity<String> addPromotion(@RequestBody PromotionDTO promotion){
        System.out.println("LOG: Start adding new promotion");
        try {
            promotionService.update(promotion);
            System.out.println("SUCCESS: Add new promotion");
            return new ResponseEntity<>("Thêm ưu đãi mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new promotion - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm ưu đãi mới thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePromotion(@RequestBody PromotionDTO promotion){
        System.out.println("LOG: Start updating promotion");
        try {
            promotionService.update(promotion);
            System.out.println("SUCCESS: Update promotion");
            return new ResponseEntity<String>("Cập nhật ưu đãi thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update promotion - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật ưu đãi thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePromotion(@Param("promotionId") Integer promotionId){
        System.out.println("LOG: Start deleting promotion");
        try {
            promotionService.remove(promotionId);
            System.out.println("SUCCESS: Delete promotion");
            return new ResponseEntity<String>("Xóa ưu đãi thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete promotion - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Xóa ưu đãi thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
}
