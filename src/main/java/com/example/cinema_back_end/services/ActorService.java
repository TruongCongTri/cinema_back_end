package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.ActorDTO;
import com.example.cinema_back_end.entities.Actor;
import com.example.cinema_back_end.repositories.IActorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class ActorService implements IActorService{

    @Autowired
    private IActorRepository actorRepository;

    @Autowired
    private ModelMapper modelMapper;

/*START-Override from IActorService*/

    /**TODO: ADMIN - MOVIE page*/
    /*START - ADMIN - update MOVIE page*/
    // dropdown list - active actors
    @Override
    public List<ActorDTO> findAllActiveActor() {
        return actorRepository.findActorsByIsActive(1)
                .stream()
                .map(actor -> modelMapper.map(actor, ActorDTO.class))
                .collect(Collectors.toList());
    }
    /*START - ADMIN - update MOVIE page*/

    @Override
    public ActorDTO findActiveById(Integer id) {
        return modelMapper.map(actorRepository.findActorByIdAndIsActive(id,1),ActorDTO.class);
    }
/*END-Override from IActorService*/

/*START-Override from IGeneralService*/
    @Override
    public List<ActorDTO> findAll() {
        return actorRepository.findAll()
                .stream()
                .map(dir -> modelMapper.map(dir, ActorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ActorDTO getById(Integer id) {
        return modelMapper.map(actorRepository.getById(id),ActorDTO.class);
    }

    @Override
    public void update(ActorDTO dir) {
        actorRepository.save(modelMapper.map(dir, Actor.class));
    }

    @Override
    public void remove(Integer id) {
        actorRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/

}
