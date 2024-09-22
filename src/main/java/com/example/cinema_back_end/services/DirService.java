package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.DirectorDTO;
import com.example.cinema_back_end.entities.Director;
import com.example.cinema_back_end.repositories.IDirRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class DirService implements IDirService{

    @Autowired
    private IDirRepository dirRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*START-Override from IDirService*/

    /**TODO: ADMIN - MOVIE page*/
    /*START - ADMIN - update MOVIE page*/
    // dropdown list - active directors
    @Override
    public List<DirectorDTO> findAllActiveDirector() {
        return dirRepository.findDirectorsByIsActive()
                .stream()
                .map(dir -> modelMapper.map(dir, DirectorDTO.class))
                .collect(Collectors.toList());
    }
    /*START - ADMIN - update MOVIE page*/

//prepare for future instalment
//user - director detail page
    @Override
    public DirectorDTO findActiveById(Integer id) {
        return modelMapper.map(dirRepository.findDirectorByIdAndIsActive(id),DirectorDTO.class);
    }
    /*END-Override from IDirService*/

    /*START-Override from IGeneralService*/
    @Override
    public List<DirectorDTO> findAll() {
        return dirRepository.findAll()
                .stream()
                .map(dir -> modelMapper.map(dir, DirectorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDTO getById(Integer id) {
        return modelMapper.map(dirRepository.getById(id),DirectorDTO.class);
    }

    @Override
    public void update(DirectorDTO dir) {
        dirRepository.save(modelMapper.map(dir, Director.class));
    }

    @Override
    public void remove(Integer id) {
        dirRepository.deleteById(id);
    }

    /*END-Override from IGeneralService*/

}
