package com.example.resource_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.resource_management.domain.Alocacao;

public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {}