package com.example.cinema_back_end.dtos;

import com.example.cinema_back_end.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tritcse00526x
 */
@Data
public class BillDTO {
    private int id;
    private LocalDateTime createdTime;
    private List<TicketDTO> tickets;
    private User user;
}
