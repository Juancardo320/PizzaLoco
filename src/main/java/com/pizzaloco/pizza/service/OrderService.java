package com.pizzaloco.pizza.service;

import com.pizzaloco.pizza.persistence.entity.OrderEntity;
import com.pizzaloco.pizza.persistence.repository.OrderRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final String DELIVERY = "D";
    private final String CARRYOUT = "C";
    private final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Método para recuperar todas las ordenes de la pizzeria
    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrder(idCustomer);
    }
}
