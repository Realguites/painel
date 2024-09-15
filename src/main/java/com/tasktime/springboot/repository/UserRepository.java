package com.tasktime.springboot.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tasktime.springboot.model.User;

public interface UserRepository extends CrudRepository<User, String> {
  Optional<User> findByUsername(String username);
}
