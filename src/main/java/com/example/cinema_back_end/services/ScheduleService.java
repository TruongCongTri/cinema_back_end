package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.BranchDTO;
import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.dtos.RoomDTO;
import com.example.cinema_back_end.dtos.ScheduleDTO;
import com.example.cinema_back_end.entities.Schedule;
import com.example.cinema_back_end.repositories.IScheduleRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class ScheduleService implements IScheduleService {
    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private ModelMapper modelMapper;

    /*START-Override from IScheduleService*/
    /**TODO: MOVIE|BRANCH page*/
    /*START - MOVIE|BRANCH page - booking*/
    @Override
    public List<String> findStartDatesOfActiveSchedules() {
        LocalDate date= LocalDate.now();
        return scheduleRepository.findStartDatesByIsActive()
                .stream().filter(localDate -> localDate.compareTo(date) >= 0)
                .map(localDate -> localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .collect(Collectors.toList());
    }
    /*END - MOVIE|BRANCH page - booking*/

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    // dropdown list - start date of active schedule based on selected movie and branch
    @Override
    public List<String> findActiveStartDatesByMovieAndBranch(Integer movieId, Integer branchId) {
        LocalDate date = LocalDate.now();
        return scheduleRepository.findStartDatesByMovieIdAndBranchIdAndIsActive(movieId,branchId)
                .stream()
                .filter(localDate -> localDate.compareTo(date) >= 0)
                .map(localDate -> localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .collect(Collectors.toList());
    }

    // dropdown list - active schedule based on selected movie and branch
    @Override
    public List<ScheduleDTO> findActiveSchedulesByMovieAndBranch(Integer movieId, Integer branchId) {
        return scheduleRepository.findSchedulesByMovieIdAndBranchIdAndIsActive(movieId, branchId)
                .stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
                .collect(Collectors.toList());
    }
    /*END - HOME page - quick booking*/

    @Override
    public List<ScheduleDTO> findSchedulesByMovie(Integer movieId) {
        return scheduleRepository.findByMovieId(movieId)
                .stream().map(schedule -> {
                    ScheduleDTO s = modelMapper.map(schedule,ScheduleDTO.class);
                    s.setBranch(modelMapper.map(s.getBranch(), BranchDTO.class));
                    s.setMovie(modelMapper.map(s.getMovie(), MovieDTO.class));
                    s.setRoom(modelMapper.map(s.getRoom(), RoomDTO.class));
                    return s;
                })
                .collect(Collectors.toList());
    }

    /**TODO: CHOOSE SEATS page*/
    /*SCHEDULE - choose seat*/
    @Override
    public ScheduleDTO findActiveSchedule(Integer id) {
        ScheduleDTO s = modelMapper.map(scheduleRepository.findScheduleByIdAndIsActive(id,1),ScheduleDTO.class);
        s.setBranch(modelMapper.map(s.getBranch(), BranchDTO.class));
        s.setMovie(modelMapper.map(s.getMovie(), MovieDTO.class));
        s.setRoom(modelMapper.map(s.getRoom(), RoomDTO.class));
        return s;
    }

    /*END-Override from IScheduleService*/

/*START-Override from IGeneralService*/
    @Override
    public List<ScheduleDTO> findAll() {
        return scheduleRepository.findAll()
                .stream().map(schedule -> {
                    ScheduleDTO s = modelMapper.map(schedule,ScheduleDTO.class);
                    s.setBranch(modelMapper.map(s.getBranch(), BranchDTO.class));
                    s.setMovie(modelMapper.map(s.getMovie(), MovieDTO.class));
                    s.setRoom(modelMapper.map(s.getRoom(), RoomDTO.class));
                    return s;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDTO getById(Integer id) {
        //ScheduleDTO s = modelMapper.map(scheduleRepository.findById(id).get(),ScheduleDTO.class);
        ScheduleDTO s = modelMapper.map(scheduleRepository.getById(id),ScheduleDTO.class);
        s.setBranch(modelMapper.map(s.getBranch(), BranchDTO.class));
        s.setMovie(modelMapper.map(s.getMovie(), MovieDTO.class));
        s.setRoom(modelMapper.map(s.getRoom(), RoomDTO.class));
        return s;
    }

    @Override
    public void update(ScheduleDTO schedule) {
        Schedule s = modelMapper.map(schedule, Schedule.class);
        s.getBranch().setId(schedule.getBranch().getId());
        s.getRoom().setId(schedule.getRoom().getId());
        s.getMovie().setId(schedule.getMovie().getId());
        scheduleRepository.save(s);
    }

    @Override
    public void remove(Integer id) {
        scheduleRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/



//INACTIVE

    //
    /*@Override
    public List<ScheduleDTO> getSchedulesByBranchId(Integer branchId) {
        return scheduleRepository.findSchedulesByBranchId(branchId)
                .stream()
                .map(schedule -> modelMapper.map(schedule,ScheduleDTO.class))
                .collect(Collectors.toList());
    }*/
}
