package com.example.cinema_back_end.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author tritcse00526x
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "rated")
public class Rated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "rated", fetch = FetchType.LAZY)
    private List<Movie> movieList;

    @Column(name = "is_active")
    private int isActive;
}
