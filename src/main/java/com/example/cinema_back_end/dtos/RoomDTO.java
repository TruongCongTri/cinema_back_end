package com.example.cinema_back_end.dtos;

import java.util.List;

import lombok.Data;

/**
 * @author tritcse00526x
 */
@Data
public class RoomDTO {
    private int id;
    private String name;

    private BranchDTO branch;
    private List<SeatDTO> seats;
    //private int isActive;
}
