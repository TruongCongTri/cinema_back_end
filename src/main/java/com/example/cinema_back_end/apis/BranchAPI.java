package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.dtos.BranchDTO;
import com.example.cinema_back_end.services.IBranchService;
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
@RequestMapping(value = "/api/branches", produces = "application/json")
public class BranchAPI {
    @Autowired
    private IBranchService branchService;

    /**TODO: BRANCH page*/
    /*START - BRANCH page*/
    @GetMapping("/active")
    public ResponseEntity<List<BranchDTO>> findAllActiveBranches() {
        System.out.println("LOG: Get all active branches");
        return new ResponseEntity<>(branchService.findAllActiveBranches(), HttpStatus.OK);
    }

    // dropdown list of branches by the selected city
    @GetMapping("/by-city")
    public ResponseEntity<List<?>> findActiveBranchesByCity(@RequestParam Integer cityId) {
        System.out.println("LOG: Get all active branches by city");
        return new ResponseEntity<>(branchService.findActiveBranchesByCity(cityId), HttpStatus.OK);
    }
    // view detail branch
    @GetMapping("/active/detail")
    public BranchDTO findActiveBranch(@RequestParam Integer cityId, @RequestParam Integer branchId){
        System.out.println("LOG: Get branch detail");
        return branchService.findBranchByCityIdAndActiveBranchId(cityId, branchId);
    }
    /*END - BRANCH page*/

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    @PostMapping
    public ResponseEntity<List<BranchDTO>> findBranchesByMovie(@Param("movieId") Integer movieId){
        System.out.println("LOG: Get all branches have active schedules by movie");
        return new ResponseEntity<>(branchService.findBranchesByMovie(movieId),HttpStatus.OK);
    }
    /*END - HOME page - quick booking*/

    /**TODO: MOVIE page*/
    /*START - MOVIE page - booking*/
    // get all branches show the selected movie
    // with schedules of each branch
    @GetMapping("/by-movie")
    private ResponseEntity<List<BranchDTO>> findBranchesShowMovie(@RequestParam Integer movieId){
        System.out.println("LOG: Get all branches have active schedules by movie");
        return new ResponseEntity<>(branchService.findBranchesShowMovieWithSchedules(movieId), HttpStatus.OK);
    }
    /*END - MOVIE page - booking*/



//INACTIVE
    /*@GetMapping("/branches-movies")
    private ResponseEntity<List<BranchDTO>> getAllBranchesAndSchedules(){
        System.out.println("SUCCESS: Get all branches and their schedules");
        return new ResponseEntity<>(branchService.getActiveBranchesAndActiveSchedules(), HttpStatus.OK);
    }*/

}
