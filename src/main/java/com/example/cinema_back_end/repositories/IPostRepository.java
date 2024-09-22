package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IPostRepository extends JpaRepository<Post, Integer> {
    //findAll
    //getById

    //admin - mange-post page
    List<Post> findPostsByTypeOrderByIdDesc(int type);

    //post page
    List<Post> findPostsByTypeAndIsActiveOrderByIdDesc(int type, int isActive);

    Post findPostByIdAndIsActive(Integer id, Integer isActive);
}
