package com.example.springboot.enums;

public enum IdentificacaoPrioridade {
    NORMAL("Normal"),
    PRIORITARIO("Prioritário");

    private String descricao;

    IdentificacaoPrioridade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
