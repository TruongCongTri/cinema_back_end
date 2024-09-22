package com.example.cinema_back_end.apis;

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
@RequestMapping(value = "api/cities", produces = "application/json")
public class CityAPI {
    @Autowired
    private ICityService cityService;

    /*START - BRANCH page*/
    //dropdown list of city has any branches
    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {
        System.out.println("LOG: Get all cities");
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }
    //dropdown list of city has any active branches
    @GetMapping("/have-active-branches")
    public ResponseEntity<List<CityDTO>> findCitiesHaveBranch() {
        System.out.println("LOG: Get all cities have active branches");
        return new ResponseEntity<>(cityService.findCitiesHaveActiveBranch(), HttpStatus.OK);
    }

    /*END - BRANCH page*/

    /**TODO: BOOK page*/
    /*START - BOOK page - quick booking*/
    // get all movies by selected city
    @GetMapping("/has-active-schedules")
    private ResponseEntity<List<CityDTO>> findCitiesHaveActiveSchedules() {
        System.out.println("LOG: Get all cities have active schedules");
        return new ResponseEntity<>(cityService.findCitiesHaveActiveSchedules(), HttpStatus.OK);
    }
    /*end - HOME page - quick booking*/
}
