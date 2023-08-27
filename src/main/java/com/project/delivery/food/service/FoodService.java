package com.project.delivery.food.service;

import com.project.delivery.food.domain.Food;
import com.project.delivery.food.dto.FoodRequestDto;
import com.project.delivery.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public Food createFood(FoodRequestDto foodRequestDto){
        return foodRepository.save(foodRequestDto.toEntity());
    }

    public Food findById(Long id) {
        return foodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
