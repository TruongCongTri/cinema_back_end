package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.dtos.RoomDTO;
import com.example.cinema_back_end.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/rooms", produces = "application/json")
public class RoomAPI {
    @Autowired
    private IRoomService roomService;

    /*INACTIVE*/
    @PostMapping
    public ResponseEntity<List<RoomDTO>> findRoomsByBranch(@Param("branchId") Integer branchId){
        System.out.println("LOG: Get all active rooms");
        return new ResponseEntity<>(roomService.findRoomsByBranch(branchId),HttpStatus.OK);
    }
}
