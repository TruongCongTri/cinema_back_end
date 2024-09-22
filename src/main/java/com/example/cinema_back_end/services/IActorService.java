package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.ActorDTO;
import com.example.cinema_back_end.dtos.DirectorDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IActorService extends IGeneralService<ActorDTO>{
    List<ActorDTO> findAllActiveActor();
    ActorDTO findActiveById(Integer id);
}
