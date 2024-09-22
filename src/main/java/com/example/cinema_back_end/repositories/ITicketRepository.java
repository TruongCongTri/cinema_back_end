package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
    //List<Ticket> findAll()
    //Ticket findById(Integer id)

    /**TODO: PROFILE page*/
    /*START - PROFILE page*/
    // get all tickets in every bill
    // purchased by user ID
    @Query("SELECT t FROM Ticket t WHERE t.bill.id IN " +
            "(SELECT b.id FROM Bill b WHERE b.user.id = :userId) " +
            "ORDER BY t.id DESC")
    List<Ticket> findTicketsByUserId(@Param("userId") Integer userId);
    /*END - PROFILE page*/

    /**TODO: SEAT SERVICE*/
    /*START - SEAT SERVICE*/
    // get all booked tickets in this schedule ID
    List<Ticket> findTicketsByScheduleId(Integer scheduleId);

    // get all booked tickets in this schedule ID
    // purchased by this user ID
    @Query("SELECT t FROM Ticket t WHERE t.bill.id IN " +
            "(SELECT b.id FROM Bill b WHERE b.user.id = :userId) " +
            "AND t.schedule.id = :scheduleId " +
            "ORDER BY t.id DESC")
    List<Ticket> findTicketsByUserIdAndScheduleId(
            @Param("userId") Integer userId,
            @Param("scheduleId") Integer scheduleId);
    /*END - SEAT SERVICE*/

    /**TODO: BILL SERVICE*/
    /*START - BILL SERVICE*/
    // get booked tickets of this seat ID in this schedule ID
    List<Ticket> findTicketsByScheduleIdAndSeatId(Integer scheduleId, Integer seatId);
    /*END - BILL SERVICE*/


    /**TODO: ADMIN - BILL page*/
    /*START - ADMIN - BILL page*/
    List<Ticket> findTicketsByBillId(Integer billId);
    /*END - ADMIN - BILL page*/

    /**TODO: ADMIN - MANAGE TICKET SERVICE*/
    /*START - TICKET SERVICE*/
    // get ticket by qrcode
    @Query("SELECT t FROM Ticket t WHERE t.qrCode = :qrCode")
    Ticket findTicketByQrCode(@Param("qrCode") String qrCode);

    /*END - TICKET SERVICE*/
}
