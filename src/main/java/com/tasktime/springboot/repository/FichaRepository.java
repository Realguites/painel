package com.tasktime.springboot.repository;

import com.tasktime.springboot.enums.IdentificacaoPrioridade;
import com.tasktime.springboot.model.Empresa;
import com.tasktime.springboot.model.Ficha;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
 public interface FichaRepository extends JpaRepository<Ficha, Long> {
    @Query("SELECT f FROM Ficha f WHERE f.empresa = ?1 AND f.identPrioridade = ?2 AND f.numero = (SELECT MAX(f2.numero) FROM Ficha f2 WHERE f2.empresa = ?1 AND f2.identPrioridade = ?2 AND f2.horarioCriacao = CURRENT_DATE)")
    Ficha findByEmpresaIdentificacaoPrioridadeDataCriacao(Empresa empresa, IdentificacaoPrioridade identificacaoPrioridade, Date dataInicial, Date dataFinal);
}
