package com.tasktime.springboot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Guiche extends BasicModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGuiche;
    private Long numero;
    private String nome;
    
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    private Usuario usuario; //ultimo usuário que trabalhou nesse guiche. geralmente usuário trabalha em um único guiche.

    public Long getIdGuiche() {
        return idGuiche;
    }

    public void setIdGuiche(Long idGuiche) {
        this.idGuiche = idGuiche;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    

}
