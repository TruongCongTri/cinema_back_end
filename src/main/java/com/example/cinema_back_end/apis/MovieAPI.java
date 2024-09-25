package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/movies", produces = "application/json")
public class MovieAPI {
    @Autowired
    private IMovieService movieService;

    /**TODO: MOVIE page*/
    /*START - MOVIE page*/
    @GetMapping("/showing/active")
    private ResponseEntity<List<MovieDTO>> findAllShowingMovies() {
        System.out.println("LOG: Get all active showing movies");
        return new ResponseEntity<>(movieService.findAllActiveShowingMovies(), HttpStatus.OK);
    }
    @GetMapping("/coming/active")
    private ResponseEntity<List<MovieDTO>> findAllComingMovies() {
        System.out.println("LOG: Get all active coming movies");
        return new ResponseEntity<>(movieService.findAllActiveComingMovies(), HttpStatus.OK);
    }

    @GetMapping("/active/detail")
    private MovieDTO findActiveMovie(@RequestParam Integer movieId) {
        System.out.println("LOG: Get movie detail");
        return movieService.findActiveMovie(movieId);
    }
    /*END - MOVIE page*/

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    // get all movies have any active schedules
    @GetMapping("/has-active-schedules")
    private ResponseEntity<List<MovieDTO>> findMoviesHaveActiveSchedules() {
        System.out.println("LOG: Get all movies have active schedules");
        return new ResponseEntity<>(movieService.findMoviesHaveActiveSchedules(), HttpStatus.OK);
    }
    /*end - HOME page - quick booking*/

    /**TODO: BOOK page*/
    /*START - BOOK page - quick booking*/
    // get all movies by selected city
    @PostMapping("/by-city")
    private ResponseEntity<List<MovieDTO>> findMoviesByCity(@RequestParam Integer cityId) {
        System.out.println("LOG: Get all movies shown in city");
        return new ResponseEntity<>(movieService.findMoviesByCity(cityId), HttpStatus.OK);
    }
    /*end - HOME page - quick booking*/

    /**TODO: BRANCH page*/
    /*START - BRANCH page - booking*/
    @GetMapping("/by-branch")
    private ResponseEntity<List<MovieDTO>> findMoviesShownInBranch(@RequestParam Integer branchId){
        System.out.println("LOG: Get all movies shown in branch");
        return new ResponseEntity<>(movieService.findMoviesShownInBranchWithSchedules(branchId), HttpStatus.OK);
    }
    /*END - BRANCH page - booking*/

//INACTIVE
    /*@GetMapping
    private ResponseEntity<List<MovieDTO>> findAll() {
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }*/

    /*@PostMapping
    private void update(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }*/

    //HOME - old quick booking - INACTIVE
/*    @GetMapping("/movies-branches")
    private ResponseEntity<List<MovieDTO>> getAllMoviesAndSchedules(){
        return new ResponseEntity<>(movieService.getMoviesAndSchedules(), HttpStatus.OK);
    }*/
}
