package com.example.resource_management.service;

import com.example.resource_management.domain.Professor;
import com.example.resource_management.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    public ResponseEntity<Professor> getProfessorById(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        return professor.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public ResponseEntity<Professor> updateProfessor(Long id, Professor professor) {
        if (!professorRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        professor.setId(id);
        return ResponseEntity.ok(professorRepository.save(professor));
    }

    public ResponseEntity<Void> deleteProfessor(Long id) {
        if (!professorRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        professorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
