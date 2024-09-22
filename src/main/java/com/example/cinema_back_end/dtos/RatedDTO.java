package com.example.cinema_back_end.dtos;

import lombok.Data;

import java.util.List;

/**
 * @author tritcse00526x
 */
@Data
public class RatedDTO {
    private int id;
    private String name;
    private String description;

    private List<MovieDTO> movies;

    private int isActive;
}
