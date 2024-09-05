package com.example.springboot.enums;

public enum TipoJuridico {
    SA("Sociedade Anônima"),
    LTDA("Sociedade Limitada"),
    EIRELI("Empresa Individual de Responsabilidade Limitada"),
    MEI("Microempreendedor Individual"),
    OUTROS("Outros");

    private final String descricao;

    TipoJuridico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}