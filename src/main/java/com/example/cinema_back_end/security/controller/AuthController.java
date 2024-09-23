package com.example.cinema_back_end.security.controller;

import com.example.cinema_back_end.entities.Role;
import com.example.cinema_back_end.entities.User;
import com.example.cinema_back_end.security.jwt.JwtResponse;
import com.example.cinema_back_end.security.jwt.JwtService;
import com.example.cinema_back_end.security.service.IRoleService;
import com.example.cinema_back_end.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            //authenticate using the username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()));
            //if no exception occurs, this indicates that the information is valid
            //set the authentication information in the Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //return the JWT to the user
            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByUsername(user.getUsername()).get();
            return ResponseEntity.ok(new
                    JwtResponse(jwt, currentUser.getId(),userDetails.getUsername(),currentUser.getFullName(),currentUser.getRoles()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sai email hoặc mật khẩu!");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            //verify whether username is existed in the database
            if(userService.findByUsername(user.getUsername()).isPresent()){
                throw new Exception("Đã tồn tại người dùng, vui lòng chọn tên đăng nhập khác");
            }
            String password = user.getPassword();
            //get roles in database by name to set for user
            Set<Role> roles = user.getRoles()
                    .stream()
                    .map(role -> {
                        Role existingRole = roleService.findByName(role.getName());
                        if(existingRole != null) {
                            return existingRole;
                        } else {
                            return role;
                        }
                    }).collect(Collectors.toSet());
            user.setRoles(roles);
            userService.save(user);

            //if no exception occurs, this indicates that the information is valid
            //set the authentication information in the Security Context
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //return the JWT to the user
            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByUsername(user.getUsername()).get();
            return ResponseEntity.ok(new
                    JwtResponse(jwt,currentUser.getId(),userDetails.getUsername(),currentUser.getFullName(),currentUser.getRoles()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
