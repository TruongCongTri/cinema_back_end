package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.ActorDTO;
import com.example.cinema_back_end.services.IActorService;
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
@RequestMapping("/api/admin/actors")
public class ManageActorAPI {
    @Autowired
    private IActorService actorService;

    /**TODO: ADMIN - update MOVIE page*/
    // dropdown list - active actors
    @GetMapping("/active")
    public ResponseEntity<List<ActorDTO>> findAllActiveActors(){
        System.out.println("LOG: get all active actors");
        return new ResponseEntity<>(actorService.findAllActiveActor(),HttpStatus.OK);
    }

    /**TODO: ADMIN - manage ACTOR page*/
    @GetMapping
    public ResponseEntity<List<ActorDTO>> findAllActors(){
        System.out.println("LOG: get all actors");
        return new ResponseEntity<>(actorService.findAll(),HttpStatus.OK);
    }
    // detail actor
    @GetMapping("/detail")
    public ActorDTO getActorDetail(@RequestParam Integer actorId){
        System.out.println("LOG: get actor detail");
        return actorService.getById(actorId);
    }
    // add new actor
    @PostMapping
    public ResponseEntity<String> addActor(@RequestBody ActorDTO actor){
        System.out.println("LOG: Start adding new actor");
        try {
            actorService.update(actor);
            System.out.println("SUCCESS: Add new actor");
            return new ResponseEntity<>("Thêm diễn viên mới thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new actor - " + e.getMessage());
            return new ResponseEntity<>("TCó lỗi xảy ra, Thêm diễn viên mới thất bại!" ,HttpStatus.BAD_REQUEST);
        }
    }
    // update existing actor
    @PutMapping
    public ResponseEntity<String> updateActor(@RequestBody ActorDTO actor){
        System.out.println("LOG: Start updating actor");
        try {
            actorService.update(actor);
            System.out.println("SUCCESS: Update actor");
            return new ResponseEntity<>("Cập nhật diễn viên thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update actor - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật diễn viên thất bại!" ,HttpStatus.BAD_REQUEST);
        }
    }
    // delete existing actor
    @DeleteMapping
    public ResponseEntity<String> deleteActor(@Param("actorId") Integer actorId){
        System.out.println("LOG: Start deleting actor");
        try {
            actorService.remove(actorId);
            System.out.println("SUCCESS: Delete actor");
            return new ResponseEntity<String>("Xóa diễn viên thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete actor - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Xóa diễn viên thất bại!" ,HttpStatus.BAD_REQUEST);
        }
    }
}
