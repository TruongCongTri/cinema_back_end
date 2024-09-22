package com.example.cinema_back_end.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


/**
 * @author tritcse00526x
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(length = 2000)
    private String smallImageURl;
    @Column(length = 2000)
    private String largeImageURL;

    @Column(name = "long_description", columnDefinition = "longtext", length = 10000)
    private String longDescription;

    //directors
    @ManyToMany(fetch = FetchType.EAGER,
            cascade= {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "movies_directors",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "dir_id")})
    private Set<Director> directors;

    //actors
    @ManyToMany(fetch = FetchType.EAGER,
            cascade= {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "movies_actors",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    private Set<Actor> actors;

    //categories
    @ManyToMany(fetch = FetchType.EAGER,
            cascade= {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "movies_genres",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> categories;

    private LocalDate releaseDate;
    private int duration;
    @Column(length = 1000)
    private String trailerURL;
    private String language;

    //rated
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rated_id", nullable = false)
    private Rated rated;

    private int isShowing;
    private int isActive;


    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Review> reviewList;

}
