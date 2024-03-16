package com.pizzaloco.pizza.service;

import com.pizzaloco.pizza.persistence.entity.PizzaEntity;
import com.pizzaloco.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired //se agrega el contructor al ser el template final, y se enyectan todas sus dependencias necesarias con autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    //creamos metodo que permita consultar todas las pizzas de la pizzeria
    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity getBiId(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public boolean exist(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
}
