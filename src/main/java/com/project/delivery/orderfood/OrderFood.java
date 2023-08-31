package com.project.delivery.orderfood;

import com.project.delivery.food.domain.Food;
import com.project.delivery.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_FOOD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}
