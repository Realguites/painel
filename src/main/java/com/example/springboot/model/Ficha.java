package com.example.springboot.model;

import java.sql.Date;
import com.example.springboot.enums.IdentificacaoPrioridade;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Ficha {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFicha;
    private Long numero;
    private IdentificacaoPrioridade identPrioridade;
    private Date horarioCriacao;

    public Long getIdFicha() {
        return idFicha;
    }
    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }
    public Long getNumero() {
        return numero;
    }
    public void setNumero(Long numero) {
        this.numero = numero;
    }
    public IdentificacaoPrioridade getIdentPrioridade() {
        return identPrioridade;
    }
    public void setIdentPrioridade(IdentificacaoPrioridade identPrioridade) {
        this.identPrioridade = identPrioridade;
    }
    public Date getHorarioCriacao() {
        return horarioCriacao;
    }
    public void setHorarioCriacao(Date horarioCriacao) {
        this.horarioCriacao = horarioCriacao;
    }

}
