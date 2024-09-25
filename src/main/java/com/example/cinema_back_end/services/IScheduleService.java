package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.ScheduleDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IScheduleService extends IGeneralService<ScheduleDTO>{
    /*MOVIE - booking*/
    List<String> findStartDatesOfActiveSchedules();
    /*MOVIE - booking*/

    /*List<ScheduleDTO> getSchedulesByBranchId(Integer branchId);*/

    /*HOME - quick booking*/
    List<ScheduleDTO> findActiveSchedulesByMovieAndBranch(Integer movieId, Integer branchId);
    List<String> findActiveStartDatesByMovieAndBranch(Integer movieId, Integer branchId);
    /*HOME - quick booking*/

    List<ScheduleDTO> findSchedulesByMovie(Integer movieId);

    /*SCHEDULE - choose seat*/
    ScheduleDTO findActiveSchedule(Integer id);
}
