package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * @author tritcse00526x
 */
public interface IBranchRepository extends JpaRepository<Branch, Integer> {
    //List<Branch> findAll()
    //Branch findById(Integer branchId)

    /**TODO: BRANCH page*/
    /*START - BRANCH page*/
    @Query("SELECT b FROM Branch b WHERE b.isActive = 1")
    List<Branch> findBranchesByIsActive();
    //also use in
    //ADMIN - SCHEDULE page - update schedule
    //ADMIN - ROOM page - update room

    // dropdown list of branches by the selected city
    @Query("SELECT b FROM Branch b WHERE b.city.id = :city_id")
    List<Branch> findBranchesByCityIdAndIsActive(@Param("city_id") Integer cityId);
    // detail branch
    @Query("SELECT b FROM Branch b " +
            "WHERE b.city.id = :city_id " +
            "AND b.id = :branch_id AND b.isActive = 1")
    Branch findBranchByCityIdAndBranchIdAndIsActive(@Param("city_id") Integer cityId, @Param("branch_id") Integer branchId);
    /*END - BRANCH page*/

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    // get all branches that have active schedules
    // based on selected movie ID
    @Query("SELECT b FROM Branch b WHERE b.id IN " +
            "(SELECT s.branch.id FROM Schedule s JOIN s.movie m " +
            "WHERE s.movie.id = :movieId)")
    List<Branch> findBranchesHaveActiveSchedulesByMovieId(@Param("movieId") Integer movieId);
    /*END - HOME page - quick booking*/

    /**TODO: MOVIE page*/
    /*START - MOVIE page - booking*/
    @Query("SELECT b FROM Branch b WHERE b.id IN " +
            "(SELECT s.branch.id FROM Schedule s JOIN s.movie m " +
            "WHERE s.movie.id = :movieId )")
    List<Branch> findBranchesAndSchedulesByMovieId(@Param("movieId") Integer movieId);
    /*END - MOVIE page - booking*/


//INACTIVE
    /*@Query("SELECT b FROM Branch b JOIN FETCH b.scheduleList WHERE b.id = (:id)")
    Branch getActiveBranchByIdAndFetchSchedulesEagerly(@Param("id") Integer id);*/
}
