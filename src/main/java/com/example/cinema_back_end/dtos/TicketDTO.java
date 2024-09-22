package com.example.cinema_back_end.dtos;

import lombok.Data;

/**
 * @author tritcse00526x
 */
@Data
public class TicketDTO {
    private int id;
    private String qrCode;
    private ScheduleDTO schedule;
    private SeatDTO seat;

    private BillDTO bill;

    private boolean isCheck;
}
