package com.tasktime.springboot.repository;

import com.tasktime.springboot.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE %?1%")
    List<Usuario> findByNomeLikeIgnoreCase(String nome);
}