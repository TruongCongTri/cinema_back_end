package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IActorRepository extends JpaRepository<Actor, Integer> {
    //List<Actor> findAll()
    //Actor findById(Integer actorId)

    List<Actor> findActorsByIsActive(Integer isActive);
    /*admin - update MOVIE*/
    Actor findActorByIdAndIsActive(Integer actorId, Integer isActive);
}
