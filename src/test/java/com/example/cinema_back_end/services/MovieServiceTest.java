package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.entities.Movie;
import com.example.cinema_back_end.repositories.IMovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author tritcse00526x
 */
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock
    ModelMapper modelMapper;
    @Mock
    private IMovieRepository movieRepository;
    @InjectMocks
    private MovieService movieService;

    private MovieDTO mockMovieDTO = new MovieDTO();
    private List<Movie> mockMovies = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @BeforeEach
    public void setUp() {

        for(int i = 0; i < 5; i++) {mockMovies.add(new Movie());}
        int id = 1;
        mockMovieDTO.setId(1);
        mockMovieDTO.setName("Xứ Cát");
        mockMovieDTO.setSmallImageURL("");
        mockMovieDTO.setLargeImageURL("");
        mockMovieDTO.setLongDescription("");
        mockMovieDTO.setReleaseDate(LocalDate.parse("24/12/2021",formatter));
        mockMovieDTO.setDuration(156);
        mockMovieDTO.setTrailerURL("");
        mockMovieDTO.setLanguage("");
        mockMovieDTO.setIsShowing(1);
        mockMovieDTO.setIsActive(1);
        mockMovieDTO.setSchedules(null);
        mockMovieDTO.setBranches(null);
        mockMovieDTO.setReviews(null);
        mockMovieDTO.setTotal(null);
        mockMovieDTO.setTotalTicket(null);
    }
    @AfterEach
    public void tearDown() {
        mockMovieDTO = null;
    }

    /*UT0001*/
    @Test
    public void  findAllActiveShowingMovies_validRequest_success() {
        // 1. create mock data
        /*List<Movie> mockMovies = new ArrayList<>();
        for(int i = 0; i < 5; i++) {mockMovies.add(new Movie());}*/
        // 2. define behavior of Repository
        when(movieRepository.findMoviesByIsShowingAndIsActiveOrderByIdDesc(1,1)).thenReturn(mockMovies);
        // 3. call service method
        List<Movie> actualMovies = movieService.findAllActiveShowingMovies().stream().map((movie) -> modelMapper.map(movie, Movie.class)).collect(Collectors.toList());
        // 4. assert the result
        assertThat(actualMovies.size()).isEqualTo(mockMovies.size());
        // 4.1 ensure repository is called
        verify(movieRepository).findMoviesByIsShowingAndIsActiveOrderByIdDesc(1,1);
    }

    /*UT0002*/
    @Test
    public void findAllActiveComingMovies_validRequest_success() {
        // 1. create mock data
        /*List<Movie> mockMovies = new ArrayList<>();
        for(int i = 0; i < 5; i++) {mockMovies.add(new Movie());}*/
        // 2. define behavior of Repository
        when(movieRepository.findMoviesByIsShowingAndIsActiveOrderByIdDesc(0,1)).thenReturn(mockMovies);
        // 3. call service method
        List<Movie> actualMovies = movieService.findAllActiveComingMovies().stream().map((movie) -> modelMapper.map(movie, Movie.class)).collect(Collectors.toList());
        // 4. assert the result
        assertThat(actualMovies.size()).isEqualTo(mockMovies.size());
        // 4.1 ensure repository is called
        verify(movieRepository).findMoviesByIsShowingAndIsActiveOrderByIdDesc(0,1);
    }

    /*UT0003*/
    @Test
    public void getMoviesHaveActiveSchedules_validRequest_success() {
        // 1. create mock data
        /*List<Movie> mockMovies = new ArrayList<>();
        for(int i = 0; i < 5; i++) {mockMovies.add(new Movie());}*/
        // 2. define behavior of Repository
        when(movieRepository.findMoviesHaveActiveSchedules()).thenReturn(mockMovies);
        // 3. call service method
        List<Movie> actualMovies = movieService.findMoviesHaveActiveSchedules().stream().map((movie) -> modelMapper.map(movie, Movie.class)).collect(Collectors.toList());
        // 4. assert the result
        assertThat(actualMovies.size()).isEqualTo(mockMovies.size());
        // 4.1 ensure repository is called
        verify(movieRepository).findMoviesHaveActiveSchedules();
    }

    @Test
    public void getById_validMovieId_success() {
        // 1. create mock data
        Integer id = 1;
        // 2. define behavior of Repository
        when(modelMapper.map(movieRepository.getById(id), MovieDTO.class)).thenReturn(mockMovieDTO);
        // 3. call service method
        MovieDTO actualMovieDTO = movieService.getById(id);
        // 4. assert the result
        assertThat(actualMovieDTO).isNotNull();
        assertThat(actualMovieDTO.getId()).isEqualTo(id);
        assertThat(actualMovieDTO.getName()).isEqualTo("Xứ Cát");
        // 4.1 ensure repository is called
        verify(movieRepository, times(2)).getById(id);
    }
    @Test
    public void getById_invalidMovieId_fail() {
        // 1. create mock data

        // 2. define behavior of Repository
        when(modelMapper.map(movieRepository.getById(any(Integer.class)), MovieDTO.class)).thenReturn(null);
        // 3. call service method
        assertThatThrownBy(() -> movieService.getById(50)).isInstanceOf(RuntimeException.class);
        // 4. assert the result

        // 4.1 ensure repository is called
        verify(movieRepository, times(2)).getById(any(Integer.class));
    }

}
