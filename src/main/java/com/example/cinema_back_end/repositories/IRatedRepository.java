package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Rated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IRatedRepository extends JpaRepository<Rated, Integer> {
    //List<Rated> findAll()
    //Rated findById(Integer ratedId)
    @Query("SELECT r FROM Rated r WHERE r.isActive = 1")
    List<Rated> findRatedsByIsActive();
    @Query("SELECT r FROM Rated r WHERE r.isActive = 1 AND r.id = :ratedId")
    Rated findRatedByIdAndIsActive(@Param("ratedId") Integer ratedId);
}
