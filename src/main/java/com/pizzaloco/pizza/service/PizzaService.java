package com.pizzaloco.pizza.service;

import com.pizzaloco.pizza.persistence.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final JdbcTemplate jdbcTemplate; //para tener consultas en nuestro servicio

    @Autowired //se agrega el contructor al ser el template final, y se enyectan todas sus dependencias necesarias con autowired
    public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //creamos metodo que permita consultar todas las pizzas de la pizzeria
    public List<PizzaEntity> getAll(){
        return jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }
}
