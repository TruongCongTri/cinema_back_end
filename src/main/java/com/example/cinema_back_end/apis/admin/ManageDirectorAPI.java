package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.DirectorDTO;
import com.example.cinema_back_end.services.IDirService;
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
@RequestMapping("/api/admin/directors")
public class ManageDirectorAPI {
    @Autowired
    private IDirService dirService;

    /**TODO: ADMIN - update MOVIE page*/
    // dropdown list - active directors
    @GetMapping("/active")
    public ResponseEntity<List<DirectorDTO>> findAllActiveDirectors(){
        System.out.println("LOG: get all active directors");
        return new ResponseEntity<>(dirService.findAllActiveDirector(),HttpStatus.OK);
    }

    /**TODO: ADMIN - manage DIRECTOR page*/
    @GetMapping
    public ResponseEntity<List<DirectorDTO>> findAllDirectors(){
        System.out.println("LOG: get all directors");
        return new ResponseEntity<>(dirService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/detail")
    public DirectorDTO getDirectorDetail(@RequestParam Integer directorId){
        System.out.println("LOG: get director detail");
        return dirService.getById(directorId);
    }

    @PostMapping
    public ResponseEntity<String> addDirector(@RequestBody DirectorDTO director){
        System.out.println("LOG: Start adding new actor");
        try {
            dirService.update(director);
            System.out.println("SUCCESS: Add new actor");
            return new ResponseEntity<>("Thêm đạo diễn mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new actor - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm đạo diễn mới thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<String> updateDirector(@RequestBody DirectorDTO director){
        System.out.println("LOG: Start updating director");
        try {
            dirService.update(director);
            System.out.println("SUCCESS: Update director");
            return new ResponseEntity<String>("Cập nhật đạo diễn thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update director - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật đạo diễn thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> deleteDirector(@Param("directorId") Integer directorId){
        System.out.println("LOG: Start deleting director");
        try {
            dirService.remove(directorId);
            System.out.println("SUCCESS: Delete director");
            return new ResponseEntity<String>("Xóa đạo diễn thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete director - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Xóa đạo diễn thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
}
