package com.example.resource_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.resource_management.domain.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {}