package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.entities.User;
import com.example.cinema_back_end.security.service.IUserService;
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
@RequestMapping(value = "/api/admin/accounts")
public class ManageAccountAPI {
    @Autowired
    private IUserService accountService;

    /**TODO: ADMIN - manage ACCOUNT page*/
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        System.out.println("LOG: Get all users");
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    // view account detail
    @GetMapping("/detail")
    public ResponseEntity<User> getUserDetail(@RequestParam Integer userId){
        System.out.println("LOG: Get user detail");
        User user = accountService.findById(userId).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // add new account
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user){
        System.out.println("LOG: Start adding new user");
        try {
            accountService.saveUserByAdmin(user, true);
            System.out.println("SUCCESS: Add new user");
            return new ResponseEntity<String>("Thêm tài khoản mới thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new user - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm tài khoản mới thất bại!" ,HttpStatus.BAD_REQUEST);
        }
    }

    // update existing account
    @PutMapping
    public ResponseEntity<String> updateUserAndCheckPassword(
            @RequestBody User user,
            @RequestParam String newPassword){
        System.out.println("LOG: Start updating user");
        try {
            if(newPassword != "") { // if has new password input
                user.setPassword(newPassword);
                accountService.saveUserByAdmin(user,true);
            } else { // if NOT
                accountService.saveUserByAdmin(user,false);
            }
            System.out.println("SUCCESS: Update user");
            return new ResponseEntity<String>("Cập nhật tài khoản thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update user - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật tài khoản thất bại!" ,HttpStatus.BAD_REQUEST);
            /*return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());*/
        }
    }

    // delete existing account
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@Param("userId") Integer userId){
        System.out.println("LOG: Start deleting user");
        try {
            accountService.remove(userId);
            System.out.println("SUCCESS: Delete user");
            return new ResponseEntity<String>("Xóa tài khoản thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete user - " + e.getMessage());
            return  new ResponseEntity<>("Có lỗi xảy ra, Xóa tài khoản thất bại!" ,HttpStatus.BAD_REQUEST);
            /*return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());*/
        }
    }

}
