package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.dtos.SeatDTO;
import com.example.cinema_back_end.services.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/seats")
public class SeatAPI {
    @Autowired
    private ISeatService seatService;

    /**TODO: SCHEDULE page*/
    /*START - SCHEDULE page*/
    // get seats' availability
    @GetMapping
    public List<SeatDTO> findSeatsBySchedule(
            @RequestParam Integer scheduleId,
            @RequestParam Integer userId){
        System.out.println("LOG: Get all seats in a room by schedule and user");
        return seatService.findSeatsByScheduleAndUser(scheduleId, userId);
    }

    // get seats layout by room of selected schedule
    @PostMapping
    public List<SeatDTO> findSeatsByRoom(@RequestParam Integer roomId){
        System.out.println("LOG: Get all seats in a room");
        return seatService.findAllSeatsByRoom(roomId);
    }

    /*END - SCHEDULE page*/
}
