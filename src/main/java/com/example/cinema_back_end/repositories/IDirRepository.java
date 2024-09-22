package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IDirRepository extends JpaRepository<Director, Integer> {
    //List<Director> findAll()
    //Director findById(Integer directorId)
    @Query("SELECT d FROM Director d WHERE d.isActive = 1")
    List<Director> findDirectorsByIsActive();

//prepare for future instalment
//user - director detail page
    @Query("SELECT d FROM Director d WHERE d.isActive = 1 AND d.id = :directorId")
    Director findDirectorByIdAndIsActive(@Param("directorId") Integer directorId);

}
