package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.SeatDTO;
import com.example.cinema_back_end.entities.Room;
import com.example.cinema_back_end.entities.Seat;
import com.example.cinema_back_end.repositories.IScheduleRepository;
import com.example.cinema_back_end.repositories.ISeatRepository;
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
public class SeatService implements ISeatService{
    @Autowired
    private ISeatRepository seatRepository;
    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private ModelMapper modelMapper;

/*START-Override from ISeatService*/
    /**TODO: SCHEDULE page*/
    /*START - SCHEDULE page*/
    @Override
    public List<SeatDTO> findSeatsByScheduleAndUser(Integer scheduleId, Integer userId) {
        // get room and all seats within a room
        Room room = scheduleRepository.getById(scheduleId).getRoom();
        List<Seat> listSeat = seatRepository.findSeatsByRoomId(room.getId());

        // get all booked seats
        List<Seat> occupiedSeats = ticketRepository.findTicketsByScheduleId(scheduleId)
                .stream().map(ticket -> ticket.getSeat())
                .collect(Collectors.toList());
        // get all seats owned by the current user
        List<Seat> checkedSeats= ticketRepository.findTicketsByUserIdAndScheduleId(userId,scheduleId)
                .stream().map(ticket -> ticket.getSeat())
                .collect(Collectors.toList());

        // get all available seats then map to dto
        List<SeatDTO> filteredSeats = listSeat
                .stream()
                .map(seat -> {
                    SeatDTO seatDTO = modelMapper.map(seat,SeatDTO.class);
                    if(occupiedSeats
                            .stream()
                            .map(occupiedSeat -> occupiedSeat.getId())
                            .collect(Collectors.toList())
                            .contains(seat.getId())){
                        seatDTO.setIsOccupied(1); //set occupied status to 1 if seat is
                    }
                    return seatDTO;
                })
                .collect(Collectors.toList());

        filteredSeats = filteredSeats
                .stream()
                .map(seat -> {
                    SeatDTO seatDTO = modelMapper.map(seat,SeatDTO.class);
                    if(checkedSeats
                            .stream()
                            .map(checkedSeat -> checkedSeat.getId())
                            .collect(Collectors.toList())
                            .contains(seat.getId())){
                        seatDTO.setChecked(true); // set checked status to TRUE if seat was booked by the current user
                    }
                    return seatDTO;
                })
                .collect(Collectors.toList());

        return  filteredSeats;
    }

    @Override
    public List<SeatDTO> findAllSeatsByRoom(Integer roomId) {
        return seatRepository.findSeatsByRoomId(roomId)
                .stream()
                .map(seat -> modelMapper.map(seat, SeatDTO.class))
                .collect(Collectors.toList());
    }
    /*END - SCHEDULE page*/

    /**TODO: ADMIN - ROOM page*/
    /*START - ROOM page*/
    // add seats to new room
    @Override
    public void addAllSeat(List<SeatDTO> seats, Integer roomId) {
        seatRepository.saveAll(seats
                .stream()
                .map(seat -> {
                    Seat seatEntity = modelMapper.map(seat, Seat.class);
                    seatEntity.setRoom(new Room());
                    seatEntity.getRoom().setId(roomId);
                    return seatEntity;
                })
                .collect(Collectors.toList()));
    }

    // update seats type
    @Override
    public void updateSeats(List<SeatDTO> seats) {
        seatRepository.saveAll(seats
                .stream()
                .map(seat -> modelMapper.map(seat, Seat.class))
                .collect(Collectors.toList()));
    }
    /*END - ROOM page*/
/*END-Override from ISeatService*/

}
