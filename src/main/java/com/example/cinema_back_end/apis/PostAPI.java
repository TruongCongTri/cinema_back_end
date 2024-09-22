package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.dtos.PostDTO;
import com.example.cinema_back_end.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostAPI {
    @Autowired
    private IPostService postService;

    @GetMapping("/reviews")
    public ResponseEntity<List<PostDTO>> findAllReviews() {
        System.out.println("LOG: Get all reviews");
        return new ResponseEntity<>(postService.findByTypeOrderByIdDesc(2),HttpStatus.OK);
    }
    @GetMapping("/news")
    public ResponseEntity<List<PostDTO>> findAllNews() {
        System.out.println("LOG: Get all news");
        return new ResponseEntity<>(postService.findByTypeOrderByIdDesc(1), HttpStatus.OK);
    }

    @GetMapping("/reviews/active")
    public ResponseEntity<List<PostDTO>> findActiveReviews() {
        System.out.println("LOG: Get all active reviews");
        return new ResponseEntity<>(postService.findAllActivePostsByType(2,1),HttpStatus.OK);
    }

    @GetMapping("/news/active")
    public ResponseEntity<List<PostDTO>> findActiveNews() {
        System.out.println("LOG: Get all active news");
        return new ResponseEntity<>(postService.findAllActivePostsByType(1,1),HttpStatus.OK);
    }
    @GetMapping("/active/detail")
    public PostDTO findActivePost(@RequestParam Integer postId) {
        System.out.println("LOG: Get post detail");
        return postService.findActivePostById(postId);
    }

}