package com.example.cinema_back_end.apis.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_back_end.dtos.ScheduleDTO;
import com.example.cinema_back_end.services.IScheduleService;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/admin/schedules", produces = "application/json")
public class ManageScheduleAPI {
    @Autowired
    private IScheduleService scheduleService;

    /**TODO: ADMIN - manage SCHEDULE page*/
    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> findAllSchedules(){
        System.out.println("LOG: get all schedules");
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ScheduleDTO getScheduleDetail(@RequestParam Integer scheduleId){
        System.out.println("LOG: get schedule detail");
        return scheduleService.getById(scheduleId);
    }

    @PostMapping
    public ResponseEntity<String> addSchedule(@RequestBody ScheduleDTO schedule){
        System.out.println("LOG: Start adding new schedule");
        try {
            scheduleService.update(schedule);
            System.out.println("SUCCESS: Add new schedule");
            return new ResponseEntity<String>("Thêm mới lịch chiếu thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Add new schedule - " + e.getMessage());
            return new ResponseEntity<String>("Có lỗi xảy ra, Thêm mới lịch chiếu thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateSchedule(@RequestBody ScheduleDTO schedule){
        System.out.println("LOG: Start updating schedule");
        try {
            scheduleService.update(schedule);
            System.out.println("SUCCESS: Update schedule");
            return new ResponseEntity<String>("Cập nhật lịch chiếu thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update schedule - " + e.getMessage());
            return new ResponseEntity<String>("Có lỗi xảy ra, Cập nhật lịch chiếu thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSchedule(@Param("scheduleId") Integer scheduleId){
        System.out.println("LOG: Start deleting schedule");
        try {
            scheduleService.remove(scheduleId);
            System.out.println("SUCCESS: Delete schedule");
            return new ResponseEntity<String>("Xóa lịch chiếu thành công!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Delete schedule - " + e.getMessage());
            return new ResponseEntity<String>("Có lỗi xảy ra, Xóa lịch chiếu thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

//prepare for future instalment
    @PostMapping("/by-movie")
	public ResponseEntity<List<ScheduleDTO>> getSchedulesByMovie(@Param("movieId") Integer movieId) {
		return new ResponseEntity<List<ScheduleDTO>>(scheduleService.findSchedulesByMovie(movieId), HttpStatus.OK);
	}
}
