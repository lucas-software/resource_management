package com.example.resource_management.domain;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private String[] horario;
    private int[] dias;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "recurso_id", nullable = false)
    private Recurso recurso;

    public Alocacao() {}

    public Alocacao(LocalDate data, String[] horario, int[] dias, Turma turma, Recurso recurso) {
        this.data = data;
        this.horario = horario;
        this.dias = dias;
        this.turma = turma;
        this.recurso = recurso;
    }

}
