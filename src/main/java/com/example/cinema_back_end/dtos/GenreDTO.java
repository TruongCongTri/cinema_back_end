package com.example.cinema_back_end.dtos;

import lombok.Data;

/**
 * @author tritcse00526x
 */
@Data
public class GenreDTO {
    private int id;
    private String name;
    private String vieName;

    private int isActive;
}
