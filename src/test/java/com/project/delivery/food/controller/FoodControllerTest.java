package com.project.delivery.food.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.delivery.food.domain.Food;
import com.project.delivery.food.dto.FoodRequestDto;
import com.project.delivery.food.repository.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodControllerTest {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        foodRepository.deleteAll();
    }

    @DisplayName("음식을 등록한다.")
    @Test
    public void createFood() throws Exception {

        final String name = "알리오올리오";
        final String category = "양식";
        final String image = "이미지 파일";
        final int price = 11000;
        final String url = "/api/food";

        final FoodRequestDto foodRequest = new FoodRequestDto(name, price, category, image);
        final String requestBody = objectMapper.writeValueAsString(foodRequest);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isCreated());

        List<Food> postList = foodRepository.findAll();

        assertThat(postList.size()).isEqualTo(1);
        assertThat(postList.get(0).getName()).isEqualTo(name);
        assertThat(postList.get(0).getCategory()).isEqualTo(category);
        assertThat(postList.get(0).getImage()).isEqualTo(image);
        assertThat(postList.get(0).getPrice()).isEqualTo(price);
    }

    @DisplayName("음식을 조회한다.")
    @Test
    public void selectFoodById() throws Exception {

        final String name = "알리오올리오";
        final String category = "양식";
        final String image = "이미지 파일";
        final int price = 11000;
        final String url = "/api/food/{id}";

        Long id = foodRepository.save(Food.builder().name(name).price(price).image(image).category(category).build()).getId();

        mockMvc.perform(get(url, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.category").value(category))
                .andExpect(jsonPath("$.image").value(image));
    }

    @DisplayName("음식을 수정한다.")
    @Test
    public void updateFoodById() throws Exception {
        final String name = "알리오올리오";
        final String category = "양식";
        final String image = "이미지 파일";
        final int price = 11000;
        final String url = "/api/food/{id}";

        Food food = foodRepository.save(Food.builder().name(name).price(price).image(image).category(category).build());

        final String newName = "투움바 파스타";
        final String newCategory = "양식";
        final String newImage = "수정된 이미지 파일";
        final int newPrice = 6000;

        FoodRequestDto updateFoodRequest = new FoodRequestDto(newName, newPrice, newCategory, newImage);
        String requestBody = objectMapper.writeValueAsString(updateFoodRequest);

        mockMvc.perform(put(url, food.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isOk());

        Food newFood = foodRepository.findById(food.getId()).get();

        assertThat(newFood.getName()).isEqualTo(newName);
        assertThat(newFood.getCategory()).isEqualTo(newCategory);
        assertThat(newFood.getImage()).isEqualTo(newImage);
        assertThat(newFood.getPrice()).isEqualTo(newPrice);
    }

    @DisplayName("음식을 삭제한다.")
    @Test
    public void deleteFoodById() throws Exception {

        final String name = "알리오올리오";
        final String category = "양식";
        final String image = "이미지 파일";
        final int price = 11000;
        final String url = "/api/food/{id}";

        Long id = foodRepository.save(Food.builder().name(name).price(price).image(image).category(category).build()).getId();

        mockMvc.perform(delete(url, id))
                .andExpect(status().isOk());

        assertThat(foodRepository.findAll()).isEmpty();
    }
}