package com.example.cinema_back_end.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * @author tritcse00526x
 */
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
