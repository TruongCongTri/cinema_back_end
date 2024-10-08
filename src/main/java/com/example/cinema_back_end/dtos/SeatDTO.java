package com.example.cinema_back_end.dtos;

import lombok.Data;

/**
 * @author tritcse00526x
 */
@Data
public class SeatDTO {
    private int id;
    private String name;

    private boolean isActive;
    private boolean isVip;
    private int isOccupied;
    private boolean isChecked;

    private RoomDTO room;
}
