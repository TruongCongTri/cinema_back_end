package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.GenreDTO;
import com.example.cinema_back_end.entities.Genre;
import com.example.cinema_back_end.repositories.IGenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class GenreService implements IGenreService{

    @Autowired
    private IGenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*START-Override from IGenreService*/
    /**TODO: ADMIN - MOVIE page*/
    /*START - ADMIN - update MOVIE page*/
    // dropdown list - active genres
    @Override
    public List<GenreDTO> findAllActiveGenres() {
        return genreRepository.findGenresByIsActive()
                .stream()
                .map(dir -> modelMapper.map(dir, GenreDTO.class))
                .collect(Collectors.toList());
    }
    /*START - ADMIN - update MOVIE page*/

//prepare for future instalment
//user - genre detail page
    @Override
    public GenreDTO findActiveById(Integer id) {
        return modelMapper.map(genreRepository.findGenreByIdAndIsActive(id),GenreDTO.class);
    }
    /*END-Override from IGenreService*/

    /*START-Override from IGeneralService*/
    @Override
    public List<GenreDTO> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(dir -> modelMapper.map(dir, GenreDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTO getById(Integer id) {
        return modelMapper.map(genreRepository.getById(id),GenreDTO.class);
    }

    @Override
    public void update(GenreDTO dir) {
        genreRepository.save(modelMapper.map(dir, Genre.class));
    }

    @Override
    public void remove(Integer id) {
        genreRepository.deleteById(id);
    }

    /*END-Override from IGeneralService*/

}
