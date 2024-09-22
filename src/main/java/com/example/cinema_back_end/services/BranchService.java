package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.BranchDTO;
import com.example.cinema_back_end.dtos.ScheduleDTO;
import com.example.cinema_back_end.entities.Branch;
import com.example.cinema_back_end.entities.Schedule;
import com.example.cinema_back_end.repositories.IBranchRepository;
import com.example.cinema_back_end.repositories.IScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class BranchService implements IBranchService {

    @Autowired
    private IBranchRepository branchRepository;
    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private ModelMapper modelMapper;

/*START-Override from IBranchService*/
    /**TODO: BRANCH page*/
    /*START - BRANCH page*/
    // also use in
    //admin - update Room
    //admin - update Schedule
    @Override
    public List<BranchDTO> findAllActiveBranches() {
        return branchRepository.findBranchesByIsActive()
                .stream()
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
    }

    // dropdown list of branches by the selected city
    @Override
    public List<BranchDTO> findActiveBranchesByCity(Integer cityId) {
        return branchRepository.findBranchesByCityIdAndIsActive(cityId)
                .stream()
                .map((branch) -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
    }
    // detail branch
    @Override
    public BranchDTO findBranchByCityIdAndActiveBranchId(Integer cityId, Integer branchId) {
        return modelMapper.map(branchRepository.findBranchByCityIdAndBranchIdAndIsActive(cityId, branchId), BranchDTO.class);
    }
    /*END - BRANCH page*/

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    @Override
    public List<BranchDTO> findBranchesByMovie(Integer movieId) {
        return branchRepository.findBranchesHaveActiveSchedulesByMovieId(movieId)
                .stream()
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
    }
    /*END - HOME page - quick booking*/

    /**TODO: MOVIE page*/
    /*START - MOVIE page - booking*/
    // get all branches show the selected movie
    // with schedules of each branch
    @Override
    public List<BranchDTO> findBranchesShowMovieWithSchedules(Integer movieId) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        List<BranchDTO> branchDTOs = new ArrayList<BranchDTO>();
        for (Branch branch : branchRepository.findBranchesAndSchedulesByMovieId(movieId)) {
            BranchDTO branchDTO = modelMapper.map(branch, BranchDTO.class);
            branchDTO.setSchedules(new LinkedList<>());
            for (Schedule schedule : branch.getScheduleList()) {
                if (schedule.getIsActive() == 1) {
                    if (schedule.getStartDate().isAfter(date) ||
                            (schedule.getStartDate().isEqual(date) && schedule.getStartTime().isAfter(time))) {
                        branchDTO.getSchedules().add(modelMapper.map(schedule, ScheduleDTO.class));
                    }
                }
            }
            if (branchDTO.getSchedules().size() > 0) {
                branchDTOs.add(branchDTO);
            }
        }
        return branchDTOs;
    }
    /*END - MOVIE page - booking*/
/*END-Override from IBranchService*/

/*START-Override from IGeneralService*/
    @Override
    public List<BranchDTO> findAll() {
        return branchRepository.findAll()
                .stream()
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BranchDTO getById(Integer id) {
        return modelMapper.map(branchRepository.getById(id), BranchDTO.class);
    }

    @Override
    public void update(BranchDTO branch) {
        branchRepository.save(modelMapper.map(branch, Branch.class));
    }

    @Override
    public void remove(Integer id) {
        branchRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/

}
