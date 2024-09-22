package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.TicketDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface ITicketService extends IGeneralService<TicketDTO> {

    List<TicketDTO> getTicketsByUser(Integer userId);

    List<TicketDTO> getTicketsByBill(Integer billId);

    TicketDTO findTicketByQrCode(String qrCode);

    void checkinTicket(String qrCode);
}
