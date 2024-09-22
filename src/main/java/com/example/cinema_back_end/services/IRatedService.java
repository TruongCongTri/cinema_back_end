package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.RatedDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IRatedService extends IGeneralService<RatedDTO>{
    List<RatedDTO> findAllActiveRateds();

//prepare for future instalment
//user - certification detail page
    RatedDTO findActiveRatedById(Integer id);
}
