package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.PostDTO;
import com.example.cinema_back_end.services.IPostService;
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
@RequestMapping("/api/admin/posts")
public class ManagePostsAPI {
    @Autowired
    private IPostService postService;

    /**TODO: ADMIN - manage POST page*/
    @GetMapping
    public ResponseEntity<List<PostDTO>> findAllPosts() {
        System.out.println("LOG: get all posts");
        return new ResponseEntity<>(postService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/detail")
    public PostDTO getPostDetail(@RequestParam Integer postId){
        System.out.println("LOG: get post detail");
        return postService.getById(postId);
    }

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody PostDTO post){
        System.out.println("LOG: Start adding new post");
        try {
            postService.update(post);
            System.out.println("SUCCESS: Add new post");
            return new ResponseEntity<String>("Thêm bài đăng mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new post - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm bài đăng mới thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePost(@RequestBody PostDTO post){
        System.out.println("LOG: Start updating post");
        try {
            postService.update(post);
            System.out.println("SUCCESS: Update post");
            return new ResponseEntity<String>("Cập nhật bài đăng thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update post - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật bài đăng thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePost(@Param("postId") Integer postId){
        System.out.println("LOG: Start deleting post");
        try {
            postService.remove(postId);
            System.out.println("SUCCESS: Delete post");
            return new ResponseEntity<String>("Xóa bài đăng thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete post - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Xóa bài đăng thất bại!", HttpStatus.BAD_REQUEST);
        }
    }
}
