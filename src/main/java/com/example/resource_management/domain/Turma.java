package com.example.resource_management.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"codigo", "disciplina_id"})})
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codigo;
    private int[] dias;
    private String[] horario;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    public Turma() {}

    public Turma(int codigo, String[] horario, Professor professor, int[] dias, Disciplina disciplina) {
        this.codigo = codigo;
        this.horario = horario;
        this.professor = professor;
        this.dias = dias;
        this.disciplina = disciplina;
    }

}