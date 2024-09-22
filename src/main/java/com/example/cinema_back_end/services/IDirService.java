package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.DirectorDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IDirService extends IGeneralService<DirectorDTO>{
    List<DirectorDTO> findAllActiveDirector();

//prepare for future instalment
//user - director detail page
    DirectorDTO findActiveById(Integer id);

}
