package com.example.cinema_back_end.dtos;

import lombok.Data;

import java.util.List;

/**
 * @author tritcse00526x
 */
@Data
public class BookingRequestDTO {
    private String orderId;
    private Integer userId;
    private Integer scheduleId;
    private List<Integer> seatIds;
    private Integer amount;
}
