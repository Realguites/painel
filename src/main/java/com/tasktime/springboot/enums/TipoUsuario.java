package com.tasktime.springboot.enums;

public enum TipoUsuario {
    MANAGER("Manager"),
    GERENTE("Gerente"),
    FUNCIONARIO("Funcionario");

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}