package com.example.cinema_back_end.dtos;

import lombok.Data;
/**
 * @author tritcse00526x
 */
@Data
public class PromotionDTO {
    private int id;
    private String title;

    private String horizontalImgUrl;
    private String verticalImgUrl;

    private String longDesc;

    private int isActive;
}
