package com.tasktime.springboot.model;

import java.sql.Date;
import com.tasktime.springboot.enums.IdentificacaoPrioridade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ficha extends BasicModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFicha;
    private Long numero;
    private IdentificacaoPrioridade identPrioridade;
    private Date horarioCriacao;

    @ManyToOne
    @JoinColumn(name = "id_empresa") // Nome da coluna FK
    private Empresa empresa;

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
    public Empresa getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    

}
