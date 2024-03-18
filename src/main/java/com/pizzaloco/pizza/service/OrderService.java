package com.pizzaloco.pizza.service;

import com.pizzaloco.pizza.persistence.entity.OrderEntity;
import com.pizzaloco.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Método para recuperar todas las ordenes de la pizzeria
    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }
}
