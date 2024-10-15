package com.example.resource_management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.resource_management.domain.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("SELECT t FROM Turma t WHERE t.professor.id = :professorId")
    List<Turma> findByProfessorId(@Param("professorId") Long professorId);
}