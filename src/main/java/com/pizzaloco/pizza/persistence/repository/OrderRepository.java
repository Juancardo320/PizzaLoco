package com.pizzaloco.pizza.persistence.repository;

import com.pizzaloco.pizza.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime date); //recuperar las ordenes de la pizzeria del dia de hoy
    List<OrderEntity> findAllByMethodIn(List<String> methods); //ordenes que han sido de pedido que no se consumieron en el local

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrder(@Param("id") String idCustomer); // ordenes que ha tenido un usuario en la pizzeria
}
