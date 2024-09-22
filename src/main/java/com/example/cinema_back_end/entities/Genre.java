package com.example.cinema_back_end.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * @author tritcse00526x
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "vie_name")
    private String vieName;

    @Column(name = "is_active")
    private int isActive;
}
