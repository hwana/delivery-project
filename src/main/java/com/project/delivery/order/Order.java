package com.project.delivery.order;

import com.project.delivery.orderfood.OrderFood;
import com.project.delivery.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderFood> orderFoodList = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setUser(User user){
        if(this.user != null){
            this.user.getOrders().remove(this);
        }

        this.user = user;
        user.getOrders().add(this);
    }

    public void addOrderFood(OrderFood orderFood){
        orderFoodList.add(orderFood);
        orderFood.setOrder(this);
    }

}
