package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.entities.Role;
import com.example.cinema_back_end.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/admin/roles")
public class ManageRoleAPI {
    @Autowired
    private IRoleService roleService;

    /**TODO: ADMIN - update ACCOUNT page*/
    // dropdown list - roles
    @GetMapping
    public ResponseEntity<List<Role>> findAllRoles(){
        System.out.println("LOG: get all roles");
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

}
