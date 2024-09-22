package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.RatedDTO;
import com.example.cinema_back_end.entities.Rated;
import com.example.cinema_back_end.repositories.IRatedRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class RatedService implements IRatedService{

    @Autowired
    private IRatedRepository ratedRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*START-Override from IRatedService*/
    /**TODO: ADMIN - MOVIE page*/
    /*START - ADMIN - update MOVIE page*/
    // dropdown list - active certifications
    @Override
    public List<RatedDTO> findAllActiveRateds() {
        return ratedRepository.findRatedsByIsActive()
                .stream()
                .map(dir -> modelMapper.map(dir, RatedDTO.class))
                .collect(Collectors.toList());
    }
    /*START - ADMIN - update MOVIE page*/

//prepare for future instalment
//user - certification detail page
    @Override
    public RatedDTO findActiveRatedById(Integer id) {
        return modelMapper.map(ratedRepository.findRatedByIdAndIsActive(id),RatedDTO.class);
    }
    /*END-Override from IRatedService*/

    /*START-Override from IGeneralService*/
    @Override
    public List<RatedDTO> findAll() {
        return ratedRepository.findAll()
                .stream()
                .map(dir -> modelMapper.map(dir, RatedDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RatedDTO getById(Integer id) {
        return modelMapper.map(ratedRepository.getById(id),RatedDTO.class);
    }

    @Override
    public void update(RatedDTO dir) {
        ratedRepository.save(modelMapper.map(dir, Rated.class));
    }

    @Override
    public void remove(Integer id) {
        ratedRepository.deleteById(id);
    }

    /*END-Override from IGeneralService*/

}
