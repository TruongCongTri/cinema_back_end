package com.example.cinema_back_end.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.cinema_back_end.entities.User;
import com.example.cinema_back_end.security.service.IUserService;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/account")
public class AccountAPI {

    @Autowired
    private IUserService userService;

    @GetMapping
    private ResponseEntity<User> getProfileUser(@RequestParam Integer userId) {
        System.out.println("LOG: Get profile");
        User user = userService.findById(userId).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/update-info")
    private ResponseEntity<String> updateProfileUser(
            @RequestParam Integer userId,
            @RequestBody User user) {
        System.out.println("LOG: Start updating profile");
        try {
            userService.updateInfo(userId,user);
        } catch (RuntimeException e) {
            System.out.println("FAIL: Update profile " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật thông tin cá nhân thất bại!", HttpStatus.EXPECTATION_FAILED);
        }
        System.out.println("SUCCESS: Update profile");
        return new ResponseEntity<>("Cập nhật thông tin cá nhân thành công!", HttpStatus.OK);
    }
    @PostMapping("/change-password")
    private ResponseEntity<String> changePasswordUser(@RequestParam Integer userId,@RequestParam String newPassword){
        System.out.println("LOG: Start changing password");
        try {
            userService.changePassword(userId, newPassword);
        } catch (RuntimeException e) {
            System.out.println("FAIL: Change password " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thay đổi mật khẩu thất bại!", HttpStatus.EXPECTATION_FAILED);
        }
        System.out.println("SUCCESS: Change password");
        return new ResponseEntity<>("Thay đổi mật khẩu thành công!", HttpStatus.OK);
    }
}
