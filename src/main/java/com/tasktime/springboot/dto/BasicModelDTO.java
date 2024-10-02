package com.tasktime.springboot.dto;

import java.util.Date;

public class BasicModelDTO {
    private Long idEntidade;
    private Date dataCadastro;
    private Date dataAlteracao;

    public Long getIdEntidade() {
        return idEntidade;

    }

    public void setIdEntidade(Long idEntidade) {
        this.idEntidade = idEntidade;
    }

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

    
}
