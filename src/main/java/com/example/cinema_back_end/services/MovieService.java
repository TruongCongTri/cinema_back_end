package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.dtos.ReviewDTO;
import com.example.cinema_back_end.dtos.ScheduleDTO;
import com.example.cinema_back_end.entities.*;
import com.example.cinema_back_end.repositories.*;
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
public class MovieService implements IMovieService{
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private IDirRepository dirRepository;
    @Autowired
    private IActorRepository actorRepository;
    @Autowired
    private IGenreRepository genreRepository;
    @Autowired
    private IRatedRepository ratedRepository;
    @Autowired
    private ModelMapper modelMapper;

/*START-Override from IMovieService*/
    /**TODO: MOVIE page*/
    /*START - MOVIE page*/
    @Override
    public List<MovieDTO> findAllActiveShowingMovies() {
        try {
            return movieRepository.findMoviesByIsShowingAndIsActiveOrderByIdDesc(1, 1)
                    .stream()
                    .map((movie) -> modelMapper.map(movie, MovieDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MovieDTO> findAllActiveComingMovies() {
        return movieRepository.findMoviesByIsShowingAndIsActiveOrderByIdDesc(0, 1)
                .stream()
                .map((movie) -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO findActiveMovie(Integer id) {
        Movie movie = movieRepository.findMovieByIdAndIsActive(id, 1);
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        movieDTO.setReviews(movie.getReviewList()
                .stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList()));
        return movieDTO;
    }
    /*END - MOVIE page*/

    /**TODO: HOME page*/
    /*START - HOME page - quick booking*/
    // get all movies have active schedules
    @Override
    public List<MovieDTO> findMoviesHaveActiveSchedules() {
        return movieRepository.findMoviesHaveActiveSchedules()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }
    /*END - HOME page - quick booking*/

    /**TODO: BRANCH page*/
    /*START - BRANCH page - booking*/
    // get all movies are shown in the selected branch
    // with schedules of each movie
    @Override
    public List<MovieDTO> findMoviesShownInBranchWithSchedules(Integer branchId) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        List<MovieDTO> movieDTOs = new ArrayList<>();
        for(Movie movie : movieRepository.findMoviesAndSchedulesByBranchId(branchId)) {
            MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
            movieDTO.setSchedules(new LinkedList<>());
            for(Schedule schedule : movie.getScheduleList()) {
                if (schedule.getIsActive() == 1) {
                    if (schedule.getStartDate().compareTo(date) > 0 ||
                            (schedule.getStartDate().compareTo(date) == 0
                                    && schedule.getStartTime().compareTo(time) > 0)) {
                        movieDTO.getSchedules().add(modelMapper.map(schedule, ScheduleDTO.class));
                    }
                }
            }
            if(movieDTO .getSchedules().size()>0) {
                movieDTOs.add(movieDTO);
            }
        }
        return movieDTOs;
    }
    /*END - BRANCH page - booking*/

    /**TODO: ADMIN - SCHEDULE page*/
    /*START - ADMIN - SCHEDULE page - update schedule */
    // dropdown list - active movies
    @Override
    public List<MovieDTO> findAllActiveMovies() {
        return movieRepository.findMoviesByIsActive(1)
                .stream()
                .map((movie) -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    /*END - ADMIN - SCHEDULE page - update schedule */
/*END-Override from IMovieService*/

/*START-Override from IGeneralService*/
    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO getById(Integer id) {
        MovieDTO movieDTO = modelMapper.map(movieRepository.getById(id), MovieDTO.class);
        try {
            if (movieDTO != null)
                return movieDTO;
            else
                throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(MovieDTO movie) {
        Movie m = modelMapper.map(movie, Movie.class);

        Set<Director> dirs = m.getDirectors()
                .stream()
                .map(dir -> {
                    Director existingDir= dirRepository.getById(dir.getId());
                    if(existingDir != null) {
                        return existingDir;
                    } else {
                        return dir;
                    }
                })
                .collect(Collectors.toSet());

        Set<Actor> actors = m.getActors()
                .stream()
                .map(act -> {
                    Actor existingAct = actorRepository.getById(act.getId());
                    if(existingAct != null) {
                        return existingAct;
                    } else {
                        return act;
                    }
                })
                .collect(Collectors.toSet());

        Set<Genre> genres = m.getCategories()
                .stream()
                .map(genre -> {
                    Genre existingGenre = genreRepository.getById(genre.getId());
                    if(existingGenre != null) {
                        return existingGenre;
                    } else {
                        return genre;
                    }
                })
                .collect(Collectors.toSet());

        m.setDirectors(dirs);
        m.setActors(actors);
        m.setCategories(genres);
        movieRepository.save(m);
    }

    @Override
    public void remove(Integer id) {
        movieRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/

//prepare for future instalment
    /**TODO: BOOK page*/
    /*START - BOOK page - quick booking*/
    // get all movies by selected city
    @Override
    public List<MovieDTO> findMoviesByCity(Integer cityId) {
        return movieRepository.findMoviesByCity(cityId)
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }
    /*END - BOOK page - quick booking*/

//INACTIVE
    //HOME - old quick booking - INACTIVE
   /* @Override
    public List<MovieDTO> getMoviesAndSchedules() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        return movieRepository.findAll().stream().map(movie -> {
                    MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
                    Map<Integer,BranchDTO> branchByMovie = new HashMap<>();
                    List<Schedule> schedules = movie.getScheduleList();
                    for (Schedule schedule : schedules) {
                        if(schedule.getStartDate().compareTo(date)>0 ||
                                (schedule.getStartDate().compareTo(date) == 0
                                        && schedule.getStartTime().compareTo(time) > 0)) {
                            if(branchByMovie.get(schedule.getBranch().getId()) == null) {
                                ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
                                scheduleDTO.getBranch().setSchedules(new ArrayList<>());
                                scheduleDTO.getBranch().getSchedules().add(modelMapper.map(schedule, ScheduleDTO.class));
                                branchByMovie.put(schedule.getBranch().getId(), scheduleDTO.getBranch());

                            } else {
                                branchByMovie.get(schedule.getBranch().getId()).getSchedules()
                                        .add(modelMapper.map(schedule, ScheduleDTO.class));
                            }
                        }
                    }
                    movieDTO.setBranches(new ArrayList<>(branchByMovie.values()));
                    return movieDTO;
                })
                .collect(Collectors.toList());
    }*/
    //
    /*@Override
    public MovieDTO getMovieAndSchedulesIsShowing(Integer id) {
        Movie movie= movieRepository.findById(id).get();
        MovieDTO movieDTO=modelMapper.map(movie, MovieDTO.class);
        LocalDate date=LocalDate.now();
        LocalTime time=LocalTime.now();
        List<ScheduleDTO> schedules=new LinkedList<>();
        for (Schedule schedule: movie.getScheduleList()) {
            if(schedule.getStartDate().compareTo(date)>0) {
                schedules.add(modelMapper.map(schedule,ScheduleDTO.class));
            }
            else if(schedule.getStartDate().compareTo(date)==0 && schedule.getStartTime().compareTo(time)>0) {
                schedules.add(modelMapper.map(schedule,ScheduleDTO.class));
            }
        }
        movieDTO.setSchedules(schedules);
        return movieDTO;
    }*/
}
