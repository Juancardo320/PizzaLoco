package com.pizzaloco.pizza.persistence.repository;

import com.pizzaloco.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice(); //pizzas disponibles

    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name); //pizza a partir de su nombre

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description); //pizzas que tengan un ingrediente
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description); //pizzas que no tengan un ingrediente
}
