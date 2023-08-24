package com.project.delivery.food.dto;

import com.project.delivery.food.domain.Food;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private final String name;
    private final int price;
    private final String category;
    private final String image;

    public FoodResponseDto(Food food) {
        this.name = food.getName();
        this.price = food.getPrice();
        this.category = food.getCategory();
        this.image = food.getImage();
    }
}
