package com.tasktime.springboot.dto;

import com.tasktime.springboot.enums.TipoUsuario;
import com.tasktime.springboot.model.BasicModel;
import com.tasktime.springboot.model.Empresa;

public class UsuarioDTO extends BasicModel{
    
    private Long idUsuario;
    private String nome;

  
    private String email;
    private TipoUsuario tipoUsuario;
    private Long idUsuarioQueCadastrou; //caso seja o primeiro usuário cadastrado, será 0;
    private Boolean needUpdatePass = true;

    private Empresa empresa;


    public Boolean getNeedUpdatePass() {
        return needUpdatePass;
    }
    public void setNeedUpdatePass(Boolean needUpdatePass) {
        this.needUpdatePass = needUpdatePass;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

}
