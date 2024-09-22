package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.CityDTO;
import com.example.cinema_back_end.services.ICityService;
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
@RequestMapping("/api/admin/cities")
public class ManageCityAPI {
    @Autowired
    private ICityService cityService;

    /**TODO: ADMIN - update BRANCH page*/
    // dropdown list - cities
    @GetMapping
    public ResponseEntity<List<CityDTO>> findAllCities(){
        System.out.println("LOG: get all cities");
        return new ResponseEntity<>(cityService.findAll(),HttpStatus.OK);
    }
}
