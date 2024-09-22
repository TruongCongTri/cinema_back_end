package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.CityDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface ICityService extends IGeneralService<CityDTO> {
    List<CityDTO> findCitiesHaveActiveBranch();
    List<CityDTO> findCitiesHaveActiveSchedules();
}
