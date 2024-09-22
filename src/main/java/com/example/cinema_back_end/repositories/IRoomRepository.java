package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IRoomRepository extends JpaRepository<Room, Integer> {
    //List<Room> findAll()
    //Room findById(Integer id)

    /**TODO: ADMIN - SCHEDULE page*/
    /*START - ADMIN - SCHEDULE page - update schedule*/
    /*@Query("SELECT r FROM Room r " +
            "WHERE r.isActive = 1 AND r.branch.id = :branchId")*/
    @Query("SELECT r FROM Room r " +
            "WHERE r.branch.id = :branchId")
    List<Room> findRoomsByBranchId(@Param("branchId") Integer branchId);
    /*END - ADMIN - SCHEDULE page - update schedule*/


//INACTIVE
/*@Query("SELECT r FROM Room r WHERE r.id IN " +
        "(SELECT s.room.id FROM Schedule s " +
        "WHERE s.movie.id = :movieId " +
        "AND s.branch.id = :branchId " +
        "AND s.startDate = :startDate " +
        "AND s.startTime = :startTime)")
List<Room> getRoomsByBranchAndMovieAndSchedule(@Param("movieId") Integer movieId,
                                               @Param("branchId") Integer branchId,
                                               @Param("startDate") LocalDate startDate,
                                               @Param("startTime") LocalTime startTime);*/
    //List<Room> findRoomsByBranchId(Integer branchId);

}
