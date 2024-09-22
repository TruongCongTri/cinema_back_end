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
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    @Column(name = "horizontal_img_url", length = 2000)
    private String horizontalImgUrl;
    @Column(name = "vertical_img_url", length = 2000)
    private String verticalImgUrl;

    @Column(name = "long_desc", columnDefinition = "longtext", length = 10000)
    private String longDesc;

    /*TYPE
    * 1 = news
    * 2 = review
    * */
    private int type;
    /*isActive
    * 0 = no
    * 1 = yes
    * */
    @Column(name = "is_active")
    private int isActive;

}
