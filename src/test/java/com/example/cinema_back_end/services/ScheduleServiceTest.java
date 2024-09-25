package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.BranchDTO;
import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.dtos.RoomDTO;
import com.example.cinema_back_end.dtos.ScheduleDTO;
import com.example.cinema_back_end.entities.Schedule;
import com.example.cinema_back_end.repositories.IScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author tritcse00526x
 */
@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {
    @Mock
    ModelMapper modelMapper;
    @Mock
    private IScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleService scheduleService;

    private ScheduleDTO mockScheduleDTO = new ScheduleDTO();

    @BeforeEach
    public void setUp() {
        mockScheduleDTO.setId(15);
        mockScheduleDTO.setStartDate(LocalDate.parse("2024-10-19"));
        mockScheduleDTO.setStartTime(LocalTime.parse("15:30"));
        mockScheduleDTO.setPrice(100000.0);
        mockScheduleDTO.setBranch(new BranchDTO());
        mockScheduleDTO.setRoom(new RoomDTO());
        mockScheduleDTO.setMovie(new MovieDTO());
    }
    @AfterEach
    public void tearDown() {mockScheduleDTO = null; }

    @Test
    public void getActiveSchedulesByMovieAndBranch_validRequest_success() {
        // 1. create mock data
        List<Schedule> mockSchedules = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Schedule schedule = new Schedule();

            mockSchedules.add(schedule);
        }
        int movieId = 15;
        int branchId = 1;
        // 2. define behavior of Repository
        when(scheduleRepository.findSchedulesByMovieIdAndBranchIdAndIsActive(movieId, branchId)).thenReturn(mockSchedules);
        // 3. call service method
        List<Schedule> actualBranches = scheduleService.findActiveSchedulesByMovieAndBranch(movieId, branchId).stream().map((schedule) -> modelMapper.map(schedule, Schedule.class)).collect(Collectors.toList());
        // 4. assert the result
        assertThat(actualBranches.size()).isEqualTo(mockSchedules.size());
        // 4.1 ensure repository is called
        verify(scheduleRepository).findSchedulesByMovieIdAndBranchIdAndIsActive(movieId, branchId);
    }


}
