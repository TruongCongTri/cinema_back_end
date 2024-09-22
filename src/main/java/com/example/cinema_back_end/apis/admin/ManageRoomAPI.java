package com.example.cinema_back_end.apis.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_back_end.dtos.RoomDTO;
import com.example.cinema_back_end.services.IRoomService;
import com.example.cinema_back_end.services.ISeatService;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/admin/rooms")
public class ManageRoomAPI {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private ISeatService seatService;

    /**TODO: ADMIN - update SCHEDULE page*/
    // dropdown list - active rooms of selected branch
    @PostMapping("/active")
    public ResponseEntity<List<RoomDTO>> findActiveRoomsByBranch(@Param("branchId") Integer branchId){
        System.out.println("LOG: get all active rooms in branch");
        return new ResponseEntity<>(roomService.findActiveRoomsByBranch(branchId),HttpStatus.OK);
    }

    /**TODO: ADMIN - manage ROOM page*/
    // get rooms of selected branch
    @PostMapping
    public ResponseEntity<List<RoomDTO>> findRoomsByBranch(@Param("branchId") Integer branchId){
        System.out.println("LOG: get all rooms in branch");
        return new ResponseEntity<>(roomService.findRoomsByBranch(branchId),HttpStatus.OK);
    }

    @GetMapping("/detail")
    public RoomDTO getRoomDetail(@RequestParam Integer roomId){
        System.out.println("LOG: get room detail");
        return roomService.getById(roomId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRoom(@RequestBody RoomDTO room){
        System.out.println("LOG: Start adding new room");
        try {
            Integer roomId = roomService.add(room).getId();
            seatService.addAllSeat(room.getSeats(),roomId);
            System.out.println("SUCCESS: Add new room");
            return new ResponseEntity<>("Thêm phòng chiếu mới thành công!",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new genre - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Thêm phòng chiếu mới thất bại!",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateRoom(@RequestBody RoomDTO room){
        System.out.println("LOG: Start updating room");
        try {
            roomService.update(room);
            seatService.updateSeats(room.getSeats());
            System.out.println("SUCCESS: Update room");
            return new ResponseEntity<>("Cập nhật phòng chiếu thành công!",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update room - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Cập nhật phòng chiếu thất bại!",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> deleteRoom(@RequestParam Integer roomId){
        System.out.println("LOG: Start deleting room");
        try {
            roomService.remove(roomId);
            System.out.println("SUCCESS: Delete room");
            return new ResponseEntity<>("Xóa phòng chiếu thành công!",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete room - " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Xóa phòng chiếu thất bại!",HttpStatus.BAD_REQUEST);
        }
    }
}
