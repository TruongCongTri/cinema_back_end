package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.BranchDTO;
import com.example.cinema_back_end.dtos.CityDTO;
import com.example.cinema_back_end.entities.Branch;
import com.example.cinema_back_end.repositories.IBranchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author tritcse00526x
 */
@ExtendWith(MockitoExtension.class)
public class BranchServiceTest {
    @Mock
    ModelMapper modelMapper;
    @Mock
    private IBranchRepository branchRepository;
    @InjectMocks
    private BranchService branchService;

    private BranchDTO mockBranch = new BranchDTO();

    List<Branch> mockBranches = new ArrayList<>();
    private CityDTO mockCity = new CityDTO();

    @BeforeEach
    public void setUp(){
        for(int i = 0; i < 5; i++) {mockBranches.add(new Branch());}
        int id = 1;
        mockBranch.setId(id);
        mockBranch.setMapURL("");
        mockBranch.setName("Nguyễn Du");
        mockBranch.setAddress("116 Đ. Nguyễn Du, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh");
        mockBranch.setHotline("19002224");
        mockBranch.setCity(mockCity);
        mockBranch.setSchedules(null);
        mockBranch.setMovies(null);
        mockBranch.setTotal(null);
        mockBranch.setTotalTicket(null);
        mockBranch.setIsActive(1);
    }
    @AfterEach
    public void tearDown() {mockBranch = null; }

    /*UT001*/
    @Test
    public void findAllActiveBranches_validRequest_success() {
        // 1. create mock data
        /*List<Branch> mockBranches = new ArrayList<>();
        for(int i = 0; i < 5; i++) {mockBranches.add(new Branch());}*/
        // 2. define behavior of Repository
        when(branchRepository.findBranchesByIsActive()).thenReturn(mockBranches);
        // 3. call service method
        List<Branch> actualBranches = branchService.findAllActiveBranches().stream().map((branch) -> modelMapper.map(branch, Branch.class)).collect(Collectors.toList());
        // 4. assert the result
        assertThat(actualBranches.size()).isEqualTo(mockBranches.size());
        // 4.1 ensure repository is called
        verify(branchRepository).findBranchesByIsActive();
    }

    /*UT002*/
    @Test
    public void getActiveBranchesByCity_validCityId_success() {
        // 1. create mock data
        /*List<Branch> mockBranches = new ArrayList<>();
        for(int i = 0; i < 5; i++) {mockBranches.add(new Branch());}*/
        // 2. define behavior of Repository
        when(branchRepository.findBranchesByCityIdAndIsActive(5)).thenReturn(mockBranches);
        // 3. call service method
        List<Branch> actualBranches = branchService.findActiveBranchesByCity(5).stream().map((branch) -> modelMapper.map(branch, Branch.class)).collect(Collectors.toList());
        // 4. assert the result
        assertThat(actualBranches.size()).isEqualTo(mockBranches.size());
        // 4.1 ensure repository is called
        verify(branchRepository).findBranchesByCityIdAndIsActive(5);
    }

    /*UT003*/
    @Test
    public void getActiveBranchesByCity_invalidCityId_fail() {
        // 1. create mock data

        // 2. define behavior of Repository
        when(branchRepository.findBranchesByCityIdAndIsActive(any(Integer.class))).thenReturn(null);
        // 3. call service method
        assertThatThrownBy(() -> branchService.findActiveBranchesByCity(50))
                .isInstanceOf(RuntimeException.class);
        // 4. assert the result

        // 4.1 ensure repository is called
        verify(branchRepository).findBranchesByCityIdAndIsActive(any(Integer.class));
    }


    @Test
    public void getBranchesByMovie_validMovieId_success() {
        // 1. create mock data
        /*List<Branch> mockBranches = new ArrayList<>();
        for(int i = 0; i < 5; i++) {mockBranches.add(new Branch());}*/
        // 2. define behavior of Repository
        when(branchRepository.findBranchesHaveActiveSchedulesByMovieId(12)).thenReturn(mockBranches);
        // 3. call service method
        List<Branch> actualBranches = branchService.findBranchesByMovie(12).stream().map((branch) -> modelMapper.map(branch, Branch.class)).collect(Collectors.toList());
        // 4. assert the result
        assertThat(actualBranches.size()).isEqualTo(mockBranches.size());
        // 4.1 ensure repository is called
        verify(branchRepository).findBranchesHaveActiveSchedulesByMovieId(12);
    }

    @Test
    public void getBranchesByMovie_invalidMovieId_fail() {
        // 1. create mock data

        // 2. define behavior of Repository
        when(branchRepository.findBranchesHaveActiveSchedulesByMovieId(any(Integer.class))).thenReturn(null);
        // 3. call service method

        assertThatThrownBy(() -> branchService.findBranchesByMovie(50))
                .isInstanceOf(RuntimeException.class);
        // 4. assert the result

        // 4.1 ensure repository is called
        verify(branchRepository).findBranchesHaveActiveSchedulesByMovieId(any(Integer.class));
    }


    @Test
    public void getBranchByCityIdAndActiveBranchId_validCityIdAndBranchId_success() {
        // 1. create mock data

        // 2. define behavior of Repository
        when(modelMapper.map(branchRepository.findBranchByCityIdAndBranchIdAndIsActive(5,1), BranchDTO.class)).thenReturn(mockBranch);
        // 3. call service method
        BranchDTO actualBranchDTO = branchService.findBranchByCityIdAndActiveBranchId(5,1);
        // 4. assert the result
        assertThat(actualBranchDTO).isNotNull();
        assertThat(actualBranchDTO.getName()).isEqualTo("Nguyễn Du");
        assertThat(actualBranchDTO.getAddress()).isEqualTo("116 Đ. Nguyễn Du, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh");
        //assertThat(actualBranchDTO.getCity().getId()).isEqualTo(5);
        // 4.1 ensure repository is called
        verify(branchRepository, times(2)).findBranchByCityIdAndBranchIdAndIsActive(5,1);
    }

}
