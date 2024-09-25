package com.example.cinema_back_end.services;

import com.example.cinema_back_end.constants.RandomString;
import com.example.cinema_back_end.dtos.*;
import com.example.cinema_back_end.entities.*;
import com.example.cinema_back_end.repositories.IBillRepository;
import com.example.cinema_back_end.repositories.IScheduleRepository;
import com.example.cinema_back_end.repositories.ISeatRepository;
import com.example.cinema_back_end.repositories.ITicketRepository;
import com.example.cinema_back_end.security.repo.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class BillService implements IBillService{

    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISeatRepository seatRepository;
    @Autowired
    private IBillRepository billRepository;
    @Autowired
    private ModelMapper modelMapper;

    /*START - Override from IBillService*/
    @Override
    @Transactional
    public List<Ticket> createNewBill(BookingRequestDTO bookingRequestDTO) throws RuntimeException {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        // get schedule of the booking request
        Schedule schedule = scheduleRepository.findScheduleByIdAndIsActive(bookingRequestDTO.getScheduleId(),1);
        if(schedule.getStartDate().compareTo(date) > 0 ||
                (schedule.getStartDate().compareTo(date) == 0 && schedule.getStartTime().compareTo(time) > 0)) { // if start date and time are greater than the current time
            // get user send the booking request
            User user = userRepository.getById(bookingRequestDTO.getUserId());

            // save bill with booking's user information
            Bill newBill = new Bill();
            newBill.setUser(user);
            newBill.setCreatedTime(LocalDateTime.now());
            Bill createdBill = billRepository.save(newBill);
            // create a new list of tickets for the bill
            List<Ticket> tickets = new ArrayList<Ticket>();

            // verify each seat
            bookingRequestDTO.getSeatIds().forEach(seatId->{
                if(!ticketRepository.findTicketsByScheduleIdAndSeatId(schedule.getId(),seatId).isEmpty()){
                    // if that seat was booked by any in that specific schedule
                    // then throw exception and roll back
                    throw new RuntimeException("Ghế đã có người đặt, vui lòng chọn lại!");
                }
                // if that seat is available to book
                // then add information to each ticket
                Ticket ticket = new Ticket();
                ticket.setSchedule(schedule);
                ticket.setSeat(seatRepository.getById(seatId));
                ticket.setBill(createdBill);
                ticket.setCheck(false);
                ticket.setQrCode(RandomString.generateString());

                tickets.add(ticketRepository.save(ticket));
            });
            return tickets;
        } else { // if schedule has passed
            // then throw exception
            throw new RuntimeException("Lịch chiếu đã kết thúc!");
        }
    }
/*END-Override from IBillService*/

/*START-Override from IGeneralService*/
    @Override
    public List<BillDTO> findAll() {
        return billRepository.findAll()
                .stream()
                .map(bill->modelMapper.map(bill, BillDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BillDTO getById(Integer billId) {
        return modelMapper.map(billRepository.getById(billId),BillDTO.class);
    }

    @Override
    public void remove(Integer id) {
        billRepository.deleteById(id);
    }

    @Override
    public void update(BillDTO t) {
    }
/*END-Override from IGeneralService*/

}
