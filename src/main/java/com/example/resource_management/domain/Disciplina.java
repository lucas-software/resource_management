package com.example.resource_management.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String name;
    private int cargaHoraria;

    public Disciplina() {}

    public Disciplina(String codigo, String name, int cargaHoraria) {
        this.codigo = codigo;
        this.name = name;
        this.cargaHoraria = cargaHoraria;
    }

}
