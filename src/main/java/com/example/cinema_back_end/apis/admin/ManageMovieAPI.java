package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.services.IMovieService;
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
@RequestMapping("/api/admin/movies")
public class ManageMovieAPI {
    @Autowired
    private IMovieService movieService;

    /**TODO: ADMIN - update SCHEDULE page*/
    // dropdown list - active movies
    @GetMapping("/active")
    public ResponseEntity<List<MovieDTO>> findAllActiveMovies(){
        System.out.println("LOG: get all active movies");
        return new ResponseEntity<>(movieService.findAllActiveMovies(),HttpStatus.OK);
    }

    /**TODO: ADMIN - manage MOVIE page*/
    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAllMovies() {
        System.out.println("LOG: get all movies");
        return new ResponseEntity<>(movieService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/detail")
    public MovieDTO getMovieDetail(@RequestParam Integer movieId){
        System.out.println("LOG: get movie detail");
        return movieService.getById(movieId);
    }

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody MovieDTO movie){
        System.out.println("LOG: Start adding new movie");
        try {
            movieService.update(movie);
            System.out.println("SUCCESS: Add new movie");
            return  new ResponseEntity<String>("Thêm phim mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new movie - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm phim mới thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateMovie(@RequestBody MovieDTO movie){
        System.out.println("LOG: Start updating movie");
        try {
            movieService.update(movie);
            System.out.println("SUCCESS: Update movie");
            return  new ResponseEntity<String>("Cập nhật phim thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update movie - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật phim thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMovie(@Param("movieId") Integer movieId){
        System.out.println("LOG: Start deleting movie");
        try {
            movieService.remove(movieId);
            System.out.println("SUCCESS: Delete movie");
            return new ResponseEntity<String>("Xóa phim thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete movie - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Xóa phim thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
}
