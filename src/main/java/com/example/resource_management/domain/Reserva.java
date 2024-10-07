package com.example.resource_management.domain;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private String[] horario;
    private int turma;

    public Reserva() {}

    public Reserva(LocalDate data, String[] horario, int turma) {
        this.data = data;
        this.horario = horario;
        this.turma = turma;
    }

}
