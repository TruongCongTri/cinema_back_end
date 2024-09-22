package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.*;
import com.example.cinema_back_end.entities.Ticket;
import com.example.cinema_back_end.repositories.ITicketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * @author tritcse00526x
 */
@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    ModelMapper modelMapper;
    @Mock
    private ITicketRepository ticketRepository;
    @InjectMocks
    private TicketService ticketService;

    private TicketDTO mockTicket = new TicketDTO();
    @BeforeEach
    public void setUp(){
        int id = 65;
        mockTicket.setId(id);
        mockTicket.setBill(new BillDTO());
        mockTicket.setSchedule(new ScheduleDTO());
        mockTicket.setSeat(new SeatDTO());
        mockTicket.setQrCode("49714ca3-b4c5-46e1-90e0-be92559f11db");
    }
    @AfterEach
    public void tearDown() {mockTicket = null; }

    @Test
    public void findTicketByQrCode_validQRCode_success() {
        // 1. create mock data
        String qrCode = "49714ca3-b4c5-46e1-90e0-be92559f11db";
        // 2. define behavior of Repository
        when(modelMapper.map(ticketRepository.findTicketByQrCode(qrCode), TicketDTO.class)).thenReturn(mockTicket);
        // 3. call service method
        TicketDTO actualTicketDTO = ticketService.findTicketByQrCode(qrCode);
        // 4. assert the result
        assertThat(actualTicketDTO).isNotNull();
        assertThat(actualTicketDTO.getQrCode()).isEqualTo("49714ca3-b4c5-46e1-90e0-be92559f11db");
        // 4.1 ensure repository is called
        verify(ticketRepository, times(2)).findTicketByQrCode(qrCode);
    }

    @Test
    public void findTicketByQrCode_invalidQRCode_fail() {
        // 1. create mock data
        String qrCode = "49714ca3";
        // 2. define behavior of Repository
        when(modelMapper.map(ticketRepository.findTicketByQrCode(any(String.class)), TicketDTO.class)).thenReturn(null);
        // 3. call service method
        //TicketDTO actualTicketDTO = ticketService.findTicketByQrCode(qrCode);
        assertThatThrownBy(() -> ticketService.findTicketByQrCode(qrCode))
                .isInstanceOf(RuntimeException.class);
        // 4. assert the result

        // 4.1 ensure repository is called
        verify(ticketRepository).findTicketByQrCode(any(String.class));
    }

    @Test
    public void getTicketsByBill_validBillId_success() {
        // 1. create mock data
        List<Ticket> mockTickets = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Ticket ticket = new Ticket();

            mockTickets.add(ticket);
        }
        Integer billId = 38;
        // 2. define behavior of Repository
        when(ticketRepository.findTicketsByBillId(billId)).thenReturn(mockTickets);
        // 3. call service method
        List<TicketDTO> actualTicketDTO = ticketService.getTicketsByBill(billId);
        // 4. assert the result
        assertThat(actualTicketDTO).isNotNull();
        assertThat(actualTicketDTO.size()).isEqualTo(mockTickets.size());
        // 4.1 ensure repository is called
        verify(ticketRepository).findTicketsByBillId(billId);
    }

    @Test
    public void getTicketsByBill_invalidBillId_fail() {
        // 1. create mock data
        Integer billId = 38;
        // 2. define behavior of Repository
        when(ticketRepository.findTicketsByBillId(any(Integer.class))).thenReturn(null);
        // 3. call service method
        //TicketDTO actualTicketDTO = ticketService.findTicketByQrCode(qrCode);
        assertThatThrownBy(() -> ticketService.getTicketsByBill(billId))
                .isInstanceOf(RuntimeException.class);
        // 4. assert the result

        // 4.1 ensure repository is called
        verify(ticketRepository).findTicketsByBillId(any(Integer.class));
    }
}
