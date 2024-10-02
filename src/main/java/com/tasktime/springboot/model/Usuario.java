package com.tasktime.springboot.model;

import java.util.Date;

import com.tasktime.springboot.enums.TipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;

    @Column(unique=true)
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Long idUsuarioQueCadastrou; //caso seja o primeiro usuário cadastrado, será 0;
    private Boolean needUpdatePass = true;

    @ManyToOne
    @JoinColumn(name = "id_empresa") // Nome da coluna FK
    private Empresa empresa;

    private Date dataCadastro;
    private Date dataAlteracao;
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Date getDataAlteracao() {
        return dataAlteracao;
    }
    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }


    public Boolean getNeedUpdatePass() {
        return needUpdatePass;
    }
    public void setNeedUpdatePass(Boolean needUpdatePass) {
        this.needUpdatePass = needUpdatePass;
    }
   
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Empresa getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public Long getIdUsuarioQueCadastrou() {
        return idUsuarioQueCadastrou;
    }
    public void setIdUsuarioQueCadastrou(Long idUsuarioQueCadastrou) {
        this.idUsuarioQueCadastrou = idUsuarioQueCadastrou;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    

}
