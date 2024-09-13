package com.tasktime.springboot.repository;

import com.tasktime.springboot.model.Smartphone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {
    @Query("SELECT s FROM Smartphone s WHERE s.nrIdentificacao = ?1")
    Smartphone findBynrIdenSmartphone(String nrIdentificacao);

}