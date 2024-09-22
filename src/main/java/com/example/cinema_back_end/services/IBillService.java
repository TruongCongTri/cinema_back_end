package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.BillDTO;
import com.example.cinema_back_end.dtos.BookingRequestDTO;
import com.example.cinema_back_end.entities.Ticket;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IBillService extends IGeneralService<BillDTO> {
    List<Ticket> createNewBill(BookingRequestDTO bookingRequestDTO) throws RuntimeException;

}
