package com.tasktime.springboot.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tasktime.springboot.model.Usuario;

public interface UserRepository extends CrudRepository<Usuario, Long> {
  Optional<Usuario> findByEmail(String username);
}
