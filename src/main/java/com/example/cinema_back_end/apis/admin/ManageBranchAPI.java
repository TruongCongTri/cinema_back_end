package com.example.cinema_back_end.apis.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_back_end.dtos.BranchDTO;
import com.example.cinema_back_end.services.IBranchService;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/admin/branches")
public class ManageBranchAPI {
    @Autowired
    private IBranchService branchService;
    /**TODO: ADMIN - update SCHEDULE/ROOM page*/
    // dropdown list - active branches
    @GetMapping("/active")
    public ResponseEntity<List<BranchDTO>> findAllActiveBranches(){
        System.out.println("LOG: get all active branches");
        return new ResponseEntity<>(branchService.findAllActiveBranches(),HttpStatus.OK);
    }

    /**TODO: ADMIN - manage BRANCH page*/
    @GetMapping
    public ResponseEntity<List<BranchDTO>> findAllBranches(){
        System.out.println("LOG: get all branches");
        return new ResponseEntity<>(branchService.findAll(),HttpStatus.OK);
    }
    // detail branch
    @GetMapping("/detail")
    public BranchDTO getBranchDetail(@RequestParam Integer branchId){
        System.out.println("LOG: get branch detail");
        return branchService.getById(branchId);
    }
    // add new branch
    @PostMapping
    public ResponseEntity<String> addBranch(@RequestBody BranchDTO branch){
        System.out.println("LOG: Start adding new branch");
        try {
            branchService.update(branch);
            System.out.println("SUCCESS: Add new branch");
            return new ResponseEntity<>("Thêm chi nhánh mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new branch - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm chi nhánh mới thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
    // update existing branch
    @PutMapping
    public ResponseEntity<String> updateBranch(@RequestBody BranchDTO branch){
        System.out.println("LOG: Start updating branch");
        try {
            branchService.update(branch);
            System.out.println("SUCCESS: Update branch");
            return new ResponseEntity<>("Cập nhật chi nhánh thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update branch - " + e.getMessage());
            return new ResponseEntity<>("Cập nhật chi nhánh không thành công!" ,HttpStatus.BAD_REQUEST);
        }
    }
    // delete existing branch
    @DeleteMapping
    public ResponseEntity<String> deleteBranch(@Param("branchId") Integer branchId){
        System.out.println("LOG: Start deleting branch");
        try {
            branchService.remove(branchId);
            System.out.println("SUCCESS: Delete branch");
            return new ResponseEntity<>("Xóa chi nhánh thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete branch - " + e.getMessage());
            return new ResponseEntity<>("Xóa chi nhánh không thành công!", HttpStatus.BAD_REQUEST);
        }
    }

}
