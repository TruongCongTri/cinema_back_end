package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.GenreDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IGenreService extends IGeneralService<GenreDTO>{
    List<GenreDTO> findAllActiveGenres();

//prepare for future instalment
//user - genre detail page
    GenreDTO findActiveById(Integer id);
}
