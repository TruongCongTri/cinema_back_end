package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.GenreDTO;
import com.example.cinema_back_end.services.IGenreService;
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
@RequestMapping("/api/admin/genres")
public class ManageGenreAPI {
    @Autowired
    private IGenreService genreService;

    /**TODO: ADMIN - update MOVIE page*/
    // dropdown list - active genres
    @GetMapping("/active")
    public ResponseEntity<List<GenreDTO>> findAllActiveGenres(){
        System.out.println("LOG: get all active genres");
        return new ResponseEntity<>(genreService.findAllActiveGenres(),HttpStatus.OK);
    }

    /**TODO: ADMIN - manage GENRE page*/
    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAllGenres(){
        System.out.println("LOG: get all genres");
        return new ResponseEntity<>(genreService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/detail")
    public GenreDTO getGenreDetail(@RequestParam Integer genreId){
        System.out.println("LOG: get genre detail");
        return genreService.getById(genreId);
    }

    @PostMapping
    public ResponseEntity<String> addGenre(@RequestBody GenreDTO genre){
        System.out.println("LOG: Start adding new genre");
        try {
            genreService.update(genre);
            System.out.println("SUCCESS: Add new genre");
            return new ResponseEntity<String>("Thêm thể loại phim mới thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new genre - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm thể loại phim mới thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<String> updateGenre(@RequestBody GenreDTO genre){
        System.out.println("LOG: Start updating genre");
        try {
            genreService.update(genre);
            System.out.println("SUCCESS: Update genre");
            return new ResponseEntity<String>("Cập nhật thể loại phim thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update genre - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật thể loại phim thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> deleteGenre(@Param("genreId") Integer genreId){
        System.out.println("LOG: Start deleting genre");
        try {
            genreService.remove(genreId);
            System.out.println("SUCCESS: Delete genre");
            return new ResponseEntity<String>("Xóa thể loại phim thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete genre - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Xóa thể loại phim thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
}
