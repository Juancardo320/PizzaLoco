package com.pizzaloco.pizza.persistence.repository;

import com.pizzaloco.pizza.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
