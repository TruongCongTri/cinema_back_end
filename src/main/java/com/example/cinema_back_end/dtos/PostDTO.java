package com.example.cinema_back_end.dtos;

import lombok.Data;

/**
 * @author tritcse00526x
 */
@Data
public class PostDTO {
    private int id;
    private String title;

    private String horizontalImgUrl;
    private String verticalImgUrl;

    private String longDesc;

    private int type;
    private int isActive;
}
