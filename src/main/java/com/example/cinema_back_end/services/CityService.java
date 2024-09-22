package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.CityDTO;
import com.example.cinema_back_end.entities.City;
import com.example.cinema_back_end.repositories.IBranchRepository;
import com.example.cinema_back_end.repositories.ICityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class CityService implements ICityService{
    @Autowired
    private ICityRepository cityRepository;
    @Autowired
    private ModelMapper modelMapper;

/*START-Override from ICityService*/
    /**TODO: BRANCH page*/
    /*START - BRANCH page*/
    //dropdown list - cities have any branches
    @Override
    public List<CityDTO> findCitiesHaveActiveBranch() {
        return cityRepository.findCitiesHaveActiveBranch()
                .stream()
                .map(city -> modelMapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }
    /*END - BRANCH page*/


    /**TODO: BOOK page*/
    /*START - BOOK page - quick booking*/
    // get all cities have active schedules
    @Override
    public List<CityDTO> findCitiesHaveActiveSchedules() {
        return cityRepository.findCitiesHaveActiveSchedules()
                .stream()
                .map(city -> modelMapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }
    /*END - BOOK page - quick booking*/

/*END-Override from ICityService*/

/*START-Override from IGeneralService*/
    @Override
    public List<CityDTO> findAll() {
        return cityRepository.findAll()
                .stream()
                .map((city) -> modelMapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CityDTO getById(Integer id) {
        return modelMapper.map(cityRepository.getById(id), CityDTO.class);
    }

    @Override
    public void update(CityDTO city) {
        cityRepository.save(modelMapper.map(city, City.class));
    }

    @Override
    public void remove(Integer id) {
        cityRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/
}
