package com.example.cinema_back_end.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author tritcse00526x
 */
@Data
public class MovieDTO {
    private int id;
    private String name;
    private String smallImageURL;
    private String longDescription;
    private String largeImageURL;

    private Set<DirectorDTO> directors;
    private Set<ActorDTO> actors;
    private Set<GenreDTO> categories;

    private LocalDate releaseDate;
    private int duration;
    private String trailerURL;
    private String language;

    private RatedDTO rated;
    private Integer isShowing;
    private List<ScheduleDTO> schedules;
    private List<BranchDTO> branches;
    private List<ReviewDTO> reviews;

    private Long total;
    private Long totalTicket;
    private int isActive;
}
