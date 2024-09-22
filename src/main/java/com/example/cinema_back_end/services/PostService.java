package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.PostDTO;
import com.example.cinema_back_end.entities.Post;
import com.example.cinema_back_end.repositories.IPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

/*START-Override from IPostService*/
    /**TODO: POST page*/
    /*START - POST page*/
    // get all active posts
    @Override
    public List<PostDTO> findAllActivePostsByType(int type, int isActive) {
        return postRepository.findPostsByTypeAndIsActiveOrderByIdDesc(type,isActive)
                .stream()
                .map((post) -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }
    // get detail of active post
    @Override
    public PostDTO findActivePostById(Integer id) {
        return modelMapper.map(postRepository.findPostByIdAndIsActive(id,1), PostDTO.class);
    }

    @Override
    public List<PostDTO> findByTypeOrderByIdDesc(int type) {
        List<PostDTO> list = new ArrayList<>();
        for (Post post : postRepository.findPostsByTypeOrderByIdDesc(type)) {
            PostDTO map = modelMapper.map(post, PostDTO.class);
            list.add(map);
        }
        return list;
    }
/*END-Override from IPostService*/

/*START-Override from IGeneralService*/
    @Override
    public List<PostDTO> findAll() {
        return postRepository.findAll()
                .stream()
                .map((post) -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getById(Integer id) { return modelMapper.map(postRepository.findById(id).orElseThrow(RuntimeException::new), PostDTO.class);}

    @Override
    public void update(PostDTO post) {
        postRepository.save(modelMapper.map(post, Post.class));
    }

    @Override
    public void remove(Integer id) {
        postRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/

}
