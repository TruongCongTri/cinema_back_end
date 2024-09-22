package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.dtos.TicketDTO;
import com.example.cinema_back_end.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketAPI {
    @Autowired
    private ITicketService ticketService;

    @GetMapping
    public List<TicketDTO> findTicketsByUser(@RequestParam Integer userId){
        System.out.println("LOG: Get all tickets by user");
        return ticketService.getTicketsByUser(userId);
    }

    @GetMapping("/detail")
    private TicketDTO findTicket(@RequestParam Integer ticketId) {
        System.out.println("LOG: Get ticket detail");
        return ticketService.getById(ticketId);
    }

}
