package com.project.delivery.food.dto;

import com.project.delivery.food.domain.Food;
import lombok.Getter;

@Getter
public class FoodRequestDto {
    private String name;
    private int price;
    private String category;
    private String image;

    public Food toEntity(){
        return Food.builder().name(name).price(price).category(category).image(image).build();
    }
}
