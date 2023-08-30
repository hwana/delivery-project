package com.project.delivery.food.dto;

import com.project.delivery.food.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDto {
    private String name;
    private int price;
    private String category;
    private String image;

    public Food toEntity(){
        return Food.builder().name(name).price(price).category(category).image(image).build();
    }
}
