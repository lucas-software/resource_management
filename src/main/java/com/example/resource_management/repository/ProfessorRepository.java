package com.example.resource_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.resource_management.domain.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {}