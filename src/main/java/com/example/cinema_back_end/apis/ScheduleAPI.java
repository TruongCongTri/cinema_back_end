package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.dtos.ScheduleDTO;
import com.example.cinema_back_end.services.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/schedule", produces = "application/json")
public class ScheduleAPI {
    @Autowired
    private IScheduleService scheduleService;

    /**TODO: SCHEDULE page*/
    /*START - SCHEDULE page*/
    @GetMapping
    public ScheduleDTO findSchedule(@RequestParam Integer scheduleId){
        System.out.println("LOG: Get schedule detail");
        return scheduleService.getById(scheduleId);
    }
    /*END - SCHEDULE page*/

    /**TODO: MOVIE|BRANCH page*/
    /*START - MOVIE|BRANCH page - booking*/
    @GetMapping("/active/all-schedule-dates")
    public List<String> findAllStartDatesOfSchedules(){
        System.out.println("LOG: Get all start dates from active schedules");
        return  scheduleService.findStartDatesOfActiveSchedules();
    }
    /*END - MOVIE|BRANCH page - booking*/

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    @PostMapping("/active/dates")
    public ResponseEntity<List<String>> findStartDatesByMovieAndBranch(
            @RequestParam Integer movieId,
            @RequestParam Integer branchId) {
        System.out.println("LOG: Get all start dates from active schedules by movie and branch");
        return new ResponseEntity<>(scheduleService.findActiveStartDatesByMovieAndBranch(movieId, branchId), HttpStatus.OK);
    }

    @PostMapping("/active")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByMovieAndBranch(
            @RequestParam Integer movieId,
            @RequestParam Integer branchId) {
        System.out.println("LOG: Get all active schedules by movie and branch");
        return new ResponseEntity<>(scheduleService.findActiveSchedulesByMovieAndBranch(movieId, branchId), HttpStatus.OK);
    }
    /*END - HOME page - quick booking*/

}
