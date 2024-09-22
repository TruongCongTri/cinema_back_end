package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {


    /**TODO: MOVIE page*/
    /**TODO: BRANCH page*/
    /*START - MOVIE|BRANCH page - booking*/
    // get all start dates of active schedules
    @Query("SELECT DISTINCT s.startDate FROM Schedule s WHERE s.isActive = 1")
    List<LocalDate> getStartDatesByIsActive();
    /*END - MOVIE|BRANCH page - booking*/
    @Query("SELECT s FROM Schedule s WHERE s.movie.id = :movieId")
    List<Schedule> findByMovieId(@Param("movieId") Integer movieId);

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    // get all start dates of active schedules
    // based on selected movie ID and branch ID
    @Query("SELECT DISTINCT s.startDate " +
            "FROM Schedule s " +
            "WHERE s.isActive = 1 " +
            "AND s.movie.id = :movieId " +
            "AND s.branch.id = :branchId")
    List<LocalDate> getStartDatesByMovieIdAndBranchIdAndIsActive(
            @Param("movieId") Integer movieId,
            @Param("branchId") Integer branchId);

    // get all schedules that are active
    // based on selected movie ID and branch ID
    @Query("SELECT DISTINCT s " +
            "FROM Schedule s " +
            "WHERE s.isActive = 1 " +
            "AND s.movie.id = :movieId " +
            "AND s.branch.id = :branchId")
    List<Schedule> getSchedulesByMovieIdAndBranchIdAndIsActive(
            @Param("movieId") Integer movieId,
            @Param("branchId") Integer branchId);
    /*END - HOME page - quick booking*/

//INACTIVE
    List<Schedule> findSchedulesByBranchId(Integer branchId);
    List<Schedule> findSchedulesByBranchIdAndRoomId(Integer branchId, Integer movieId);
    List<Schedule> findSchedulesByBranchIdAndRoomIdAndMovieId(Integer branchId, Integer roomId, Integer movieId);

    /*@Query("SELECT DISTINCT s.startTime " +
            "FROM Schedule s " +
            "WHERE s.isActive = 1 " +
            "AND s.movie.id = :movieId " +
            "AND s.branch.id = :branchId " +
            "AND s.startDate = :startDate")
    List<LocalTime> getActiveStartTimesByMovieIdAndBranchIdAndStartDate(
            @Param("movieId") Integer movieId,
            @Param("branchId") Integer branchId,
            @Param("startDate") LocalDate startDate);

    @Query("SELECT s FROM Schedule s WHERE s.isActive = 1")
    List<Schedule> findAllActiveSchedules();

    @Query("SELECT s FROM Schedule s WHERE s.isActive = 1 AND s.id = :scheduleId")
    Schedule findActiveById(@Param("scheduleId") Integer scheduleId);
    */
}
