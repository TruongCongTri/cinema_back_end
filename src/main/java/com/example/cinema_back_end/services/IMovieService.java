package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.MovieDTO;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IMovieService extends IGeneralService<MovieDTO> {
    /**/
    List<MovieDTO> findAllActiveShowingMovies();
    List<MovieDTO> findAllActiveComingMovies();
    MovieDTO findActiveMovie(Integer id);
    /**/
   /* MovieDTO getMovieAndSchedules(Integer id);
    MovieDTO getMovieAndSchedulesIsShowing(Integer id);*/

    /*branch-booking session*/
    List<MovieDTO> findMoviesShownInBranchWithSchedules(Integer branchId);

    /*home-quick booking*/
    List<MovieDTO> findMoviesHaveActiveSchedules();

    /*admin - schedule page*/
    List<MovieDTO> findAllActiveMovies();

    /*book page*/
    List<MovieDTO> findMoviesByCity(Integer cityId);
}
