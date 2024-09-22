package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.SeatDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface ISeatService {

    List<SeatDTO> findSeatsByScheduleAndUser(Integer scheduleId, Integer userId);
    List<SeatDTO> findAllSeatsByRoom(Integer roomId);
    void addAllSeat(List<SeatDTO> seats,Integer roomId);
    void updateSeats(List<SeatDTO> seats);
}
