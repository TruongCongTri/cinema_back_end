package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.RatedDTO;
import com.example.cinema_back_end.services.IRatedService;
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
@RequestMapping("/api/admin/certifications")
public class ManageRatedAPI {
    @Autowired
    private IRatedService ratedService;

    /**TODO: ADMIN - update MOVIE page*/
    // dropdown list - active certifications
    @GetMapping("/active")
    public ResponseEntity<List<RatedDTO>> findAllActiveCertifications(){
        System.out.println("LOG: get all active rating");
        return new ResponseEntity<>(ratedService.findAllActiveRateds(),HttpStatus.OK);
    }

    /**TODO: ADMIN - manage RATED page*/
    @GetMapping
    public ResponseEntity<List<RatedDTO>> findAllCertifications(){
        System.out.println("LOG: get all rating");
        return new ResponseEntity<>(ratedService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/detail")
    public RatedDTO getCertificationDetail(@RequestParam Integer certificateId){
        System.out.println("LOG: get rating detail");
        return ratedService.getById(certificateId);
    }

    @PostMapping
    public ResponseEntity<String> addCertification(@RequestBody RatedDTO certificate){
        System.out.println("LOG: Start adding new rating");
        try {
            ratedService.update(certificate);
            System.out.println("SUCCESS: Add new rating");
            return new ResponseEntity<>("Thêm phân loại độ tuổi mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new rating - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm phân loại độ tuổi mới thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<String> updateCertification(@RequestBody RatedDTO certificate){
        System.out.println("LOG: Start updating rating");
        try {
            ratedService.update(certificate);
            System.out.println("SUCCESS: Update rating");
            return new ResponseEntity<>("Cập nhật phân loại độ tuổi thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update rating - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật loại độ tuổi thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> deleteCertification(@Param("certificateId") Integer certificateId){
        System.out.println("LOG: Start deleting rating");
        try {
            ratedService.remove(certificateId);
            System.out.println("SUCCESS: Delete rating");
            return new ResponseEntity<>("Xóa phân loại độ tuổi thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete rating - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Xóa phân loại độ tuổi thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
}
