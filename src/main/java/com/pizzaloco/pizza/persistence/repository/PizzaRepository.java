package com.pizzaloco.pizza.persistence.repository;

import com.pizzaloco.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
}
