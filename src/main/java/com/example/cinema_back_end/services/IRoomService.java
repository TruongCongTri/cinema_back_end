package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.RoomDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IRoomService extends IGeneralService<RoomDTO> {

    //List<RoomDTO> getRooms(Integer movieId,Integer branchId,String startDate,String startTime);
    List<RoomDTO> findRoomsByBranch(Integer branchId);
    List<RoomDTO> findActiveRoomsByBranch(Integer branchId);
    RoomDTO add(RoomDTO room);

}
