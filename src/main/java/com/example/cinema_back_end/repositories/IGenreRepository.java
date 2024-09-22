package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IGenreRepository extends JpaRepository<Genre, Integer> {
    //List<Genre> findAll()
    //Genre findById(Integer genreId)
    @Query("SELECT g FROM Genre g WHERE g.isActive = 1")
    List<Genre> findGenresByIsActive();
    @Query("SELECT g FROM Genre g WHERE g.isActive = 1 AND g.id = :genreId")
    Genre findGenreByIdAndIsActive(@Param("genreId") Integer genreId);
}
