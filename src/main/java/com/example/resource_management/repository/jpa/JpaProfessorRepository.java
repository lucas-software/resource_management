package com.example.resource_management.repository.jpa;

import com.example.resource_management.domain.Professor;
import com.example.resource_management.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JpaProfessorRepository implements ProfessorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Professor> findProfessorById(Long id) {
        Professor professor = entityManager.find(Professor.class, id);
        return Optional.ofNullable(professor);
    }
}
