package com.tasktime.springboot.repository;

import com.tasktime.springboot.model.Empresa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query("SELECT u FROM Empresa u WHERE LOWER(u.razaoSocial) LIKE %?1%")
    List<Empresa> findByRazaoSocialLikeIgnoreCase(String razaoSocial);

    @Query("SELECT u FROM Empresa u WHERE LOWER(u.cnpj) LIKE %?1%")
    List<Empresa> findByCnpjLikeIgnoreCase(String cnpj);
}