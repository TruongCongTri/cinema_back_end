package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.RoomDTO;
import com.example.cinema_back_end.entities.Room;
import com.example.cinema_back_end.repositories.IRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*START-Override from IRoomService*/
    /**TODO: ADMIN - ROOM page*/
    /*START - ADMIN - ROOM page - add room*/
    @Override
    public RoomDTO add(RoomDTO room) {
        return modelMapper.map(roomRepository.save(modelMapper.map(room, Room.class)), RoomDTO.class);
    }
    /*END - ADMIN - ROOM page - add room*/

    /**TODO: ADMIN - SCHEDULE page*/
    /*START - ADMIN - SCHEDULE page - update schedule*/
    @Override
    public List<RoomDTO> findActiveRoomsByBranch(Integer branchId) {
        return roomRepository.findRoomsByBranchId(branchId)
                .stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }
    /*END - ADMIN - SCHEDULE page - update schedule*/

    //
    public List<RoomDTO> findRoomsByBranch(Integer branchId){
        return roomRepository.findRoomsByBranchId(branchId)
                .stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }
/*END-Override from IRoomService*/

/*START-Override from IGeneralService*/
    @Override
    public List<RoomDTO> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoomDTO getById(Integer id) {
        return modelMapper.map(roomRepository.getById(id), RoomDTO.class);
    }

    @Override
    public void update(RoomDTO room) {
        roomRepository.save(modelMapper.map(room, Room.class));
    }

    @Override
    public void remove(Integer id) {
        roomRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/



//INACTIVE
    //
    /*@Override
    public List<RoomDTO> getRooms(Integer movieId, Integer branchId, String startDate, String startTime) {
        return roomRepository.getRoomsByBranchAndMovieAndSchedule(movieId, branchId, LocalDate.parse(startDate), LocalTime.parse(startTime))
                .stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }*/


}
