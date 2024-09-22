package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.SeatDTO;
import com.example.cinema_back_end.services.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/admin/seats", produces = "application/json")
public class ManageSeatAPI {
    @Autowired
    private ISeatService seatService;

    /**TODO: ADMIN - manage ROOM page*/
    @PostMapping
    public ResponseEntity<List<SeatDTO>> findAllSeatsByRoom(@Param("roomId") Integer roomId) {
        System.out.println("LOG: get all seats by room");
        return new ResponseEntity<>(seatService.findAllSeatsByRoom(roomId),HttpStatus.OK);
    }

}
