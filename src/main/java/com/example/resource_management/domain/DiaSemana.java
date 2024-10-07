package com.example.resource_management.domain;

public enum DiaSemana {
    SEGUNDA(1),
    TERCA(2),
    QUARTA(3),
    QUINTA(4),
    SEXTA(5);

    private final int valor;

    DiaSemana(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}