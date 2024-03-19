package com.pizzaloco.pizza.persistence.repository;

import com.pizzaloco.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice(); //pizzas disponibles
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name); //pizza a partir de su nombre
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description); //pizzas que tengan un ingrediente
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description); //pizzas que no tengan un ingrediente
    int countByVeganTrue(); //cuantas pizzas veganas ofrece la pizzeria
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price); //Pizzas mas economicas de la pizzeria
}
