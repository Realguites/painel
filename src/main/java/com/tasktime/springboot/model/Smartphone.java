package com.tasktime.springboot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Smartphone extends BasicModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSmartphone;

    @Column(unique=true)
    private String nrIdentificacao;
    private boolean ativo;

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_empresa", referencedColumnName = "idEmpresa") 
    private Empresa empresa;


    public Long getIdSmartphone() {
        return idSmartphone;
    }
    public void setIdSmartphone(Long idSmartphone) {
        this.idSmartphone = idSmartphone;
    }
    public String getNrIdentificacao() {
        return nrIdentificacao;
    }
    public void setNrIdentificacao(String nrIdentificacao) {
        this.nrIdentificacao = nrIdentificacao;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public Empresa getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
