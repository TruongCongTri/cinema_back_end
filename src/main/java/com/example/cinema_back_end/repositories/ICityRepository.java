package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface ICityRepository extends JpaRepository<City, Integer> {
    //List<City> findAll
    //City findById(Integer cityId)

    @Query("SELECT DISTINCT b.city FROM Branch b WHERE b.isActive = 1")
    List<City> findCitiesHaveActiveBranch();

    /**TODO: BOOK page*/
    /*START - BOOK page - quick booking*/
    @Query("SELECT c FROM City c WHERE c.id IN " +
            "(SELECT s.branch.city.id FROM Schedule s WHERE s.isActive = 1)")
    List<City> findCitiesHaveActiveSchedules();
    /*END - BOOK page - quick booking*/
}
