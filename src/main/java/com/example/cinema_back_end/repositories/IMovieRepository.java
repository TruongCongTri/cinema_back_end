package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    //List<Movie> findAll()
    //Movie findById(Integer movieId)

    /**TODO: MOVIE page*/
    /*START - MOVIE page*/
    List<Movie> findMoviesByIsShowingAndIsActiveOrderByIdDesc(Integer isShowing, Integer isActive);
    Movie findMovieByIdAndIsActive(Integer id, Integer isActive);
    /*END - MOVIE page*/

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    // get all movies that have active schedules
    @Query("SELECT m FROM Movie m WHERE m.id IN " +
            "(SELECT s.movie.id FROM Schedule s WHERE s.isActive = 1)")
    List<Movie> findMoviesHaveActiveSchedules();
    /*END - HOME page - quick booking*/

    /**TODO: BRANCH page*/
    /*START - BRANCH page - booking*/
    @Query("SELECT m FROM Movie m WHERE m.id IN " +
            "(SELECT s.movie.id FROM Schedule s JOIN s.branch b WHERE s.branch.id = :branchId)")
    List<Movie> findMoviesAndSchedulesByBranchId(@Param("branchId") Integer branchId);
    /*END - BRANCH page - booking*/

    /**TODO: ADMIN - SCHEDULE page*/
    /*START - ADMIN - SCHEDULE page - update schedule */
    // dropdown list - active movies
    List<Movie> findMoviesByIsActive(Integer isActive);
    /*END - ADMIN - SCHEDULE page - update schedule */

//preserve for future instalment
    /**TODO: BOOK page*/
    /*START - BOOK page - quick booking*/
    @Query("SELECT m FROM Movie m WHERE m.id IN " +
            "(SELECT s.movie.id FROM Schedule s " +
            "WHERE s.branch.city.id = :cityId)")
    List<Movie> findMoviesByCity(@Param("cityId") Integer cityId);
    /*END - BOOK page - quick booking*/

//INACTIVE
    /*END-Movie page*/
//    /*@Query("SELECT m FROM Movie m JOIN FETCH m.scheduleList WHERE m.id = (:id)")
//    Movie getMovieByIdAndFetchSchedulesEagerly(@Param("id") Integer id);
//    @Query("SELECT m FROM Movie m WHERE m.id IN " +
//            "(SELECT s.movie.id FROM Schedule s WHERE s.branch.id = :branchId)")
//    List<Movie> getMoviesByBranch(@Param("branchId") Integer branchId);
//    @Query("SELECT m FROM Movie m WHERE m.id IN " +
//            "(SELECT s.movie.id FROM Schedule s WHERE s.branch.id = :branchId AND s.room.id = :roomId)")
//    List<Movie> getMoviesByBranchAndRoom(@Param("branchId") Integer branchId, @Param("roomId") Integer roomId);*/
    /*END-Branch page*/

    /*START-Movie page*/
    //search by movie name
    //with status
    /*List<Movie> findMoviesByIsShowingAndNameContaining(Integer isShowing,String name);*/

    //without status
    /*List<Movie> findMoviesByNameContaining(String name);*/
}
