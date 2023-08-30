package com.project.delivery.food.controller;

import com.project.delivery.food.domain.Food;
import com.project.delivery.food.dto.FoodRequestDto;
import com.project.delivery.food.dto.FoodResponseDto;
import com.project.delivery.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.ObjectEqualityComparator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/food")
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody FoodRequestDto foodRequestDto){
        Food food = foodService.createFood(foodRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(food);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> selectFoodById(@PathVariable Long id){
        Food food = foodService.findById(id);
        return ResponseEntity.ok().body(new FoodResponseDto(food));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodById(@PathVariable Long id, @RequestBody FoodRequestDto foodRequestDto){
        Food food = foodService.updateFood(id, foodRequestDto);
        return ResponseEntity.ok(food);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFoodById(@PathVariable Long id){
        foodService.deleteFood(id);
        return ResponseEntity.ok().build();
    }
}
