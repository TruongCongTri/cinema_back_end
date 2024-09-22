package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.TicketDTO;
import com.example.cinema_back_end.entities.Ticket;
import com.example.cinema_back_end.repositories.ITicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class TicketService implements ITicketService{
    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private ModelMapper modelMapper;

/*START-Override from ITicketService*/
    /**TODO: PROFILE page*/
    /*START - PROFILE page*/
    // get all transactions
    @Override
    public List<TicketDTO> getTicketsByUser(Integer userId) {
        return ticketRepository.findTicketsByUserId(userId)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }
    /*END - PROFILE page*/

    /**TODO: ADMIN - BILL page*/
    /*START - ADMIN - BILL page*/
    @Override
    public List<TicketDTO> getTicketsByBill(Integer billId) {
        return ticketRepository.findTicketsByBillId(billId)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }
    /*END - ADMIN - BILL page*/

    /**TODO: ADMIN - CHECK IN PAGE*/
    /*START - ADMIN - CHECK IN page*/
    @Override
    public TicketDTO findTicketByQrCode(String qrCode) throws RuntimeException {
        TicketDTO ticketDTO = modelMapper.map(ticketRepository.findTicketByQrCode(qrCode), TicketDTO.class);

        try {
            if (ticketDTO != null)
                return ticketDTO;
            else
                throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void checkinTicket(String qrCode) throws RuntimeException{
        try {
            Ticket ticket = ticketRepository.findTicketByQrCode(qrCode);
            if (ticket != null) {
                ticket.setCheck(true);
                ticketRepository.save(ticket);
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*END - ADMIN - CHECK IN page*/
/*END-Override from ITicketService*/


/*START-Override from IGeneralService*/
    @Override
    public List<TicketDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Integer id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public TicketDTO getById(Integer id) {
        return modelMapper.map(ticketRepository.getById(id), TicketDTO.class);
    }

    @Override
    public void update(TicketDTO ticket) {
        Ticket t = modelMapper.map(ticket, Ticket.class);
        t.getBill().setId(ticket.getBill().getId());
        t.getSchedule().setId(ticket.getSchedule().getId());
        t.getSeat().setId(ticket.getSeat().getId());
        ticketRepository.save(t);
    }
/*END-Override from IGeneralService*/
}
