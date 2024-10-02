package com.example.resource_management.service;

import com.example.resource_management.domain.Disciplina;
import com.example.resource_management.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public ResponseEntity<Disciplina> getDisciplinaById(Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        return disciplina.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public ResponseEntity<Disciplina> updateDisciplina(Long id, Disciplina disciplina) {
        if (!disciplinaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        disciplina.setId(id);
        return ResponseEntity.ok(disciplinaRepository.save(disciplina));
    }

    public ResponseEntity<Void> deleteDisciplina(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        disciplinaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
