package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.PostDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IPostService extends IGeneralService<PostDTO> {

    List<PostDTO> findByTypeOrderByIdDesc(int type);
    List<PostDTO> findAllActivePostsByType(int type, int isActive);
    PostDTO findActivePostById(Integer id);
}
