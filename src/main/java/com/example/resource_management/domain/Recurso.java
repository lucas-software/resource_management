package com.example.resource_management.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codigo;
    private String descricao;

    public Recurso() {}

    public Recurso(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
