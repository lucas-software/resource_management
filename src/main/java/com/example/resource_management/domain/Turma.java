package com.example.resource_management.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codigo;
    private String horario;

    private String professor;

    public Turma() {}

    public Turma(int codigo, String horario, String professor) {
        this.codigo = codigo;
        this.horario = horario;
        this.professor = professor;
    }

}
