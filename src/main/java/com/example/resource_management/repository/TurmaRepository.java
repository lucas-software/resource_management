package com.example.resource_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.resource_management.domain.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {}