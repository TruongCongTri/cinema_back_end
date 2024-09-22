package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.BranchDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IBranchService extends IGeneralService<BranchDTO>{
    //List<BranchDTO> findAll;
    //BranchDTO findById(Integer branchId);

    /*user - view Branches*/
    // also use in
    //admin - update Room
    //admin - update Schedule
    List<BranchDTO> findAllActiveBranches();
    /*user - view Branches*/

    /*user - dropdown list to select city and branch*/
    List<BranchDTO> findActiveBranchesByCity(Integer cityId);
    BranchDTO findBranchByCityIdAndActiveBranchId(Integer cityId, Integer branchId);
    /*user - dropdown list to select city and branch*/

    /*user - movie booking*/
    List<BranchDTO> findBranchesShowMovieWithSchedules(Integer movieId);
    /*user - movie booking*/

    /*user - home quick booking*/
    List<BranchDTO> findBranchesByMovie(Integer movieId);
    /*user - home quick booking*/
}
