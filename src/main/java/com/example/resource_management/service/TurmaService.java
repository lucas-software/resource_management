package com.example.resource_management.service;

import com.example.resource_management.domain.Turma;
import com.example.resource_management.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public ResponseEntity<Turma> getTurmaById(Long id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        return turma.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    
    public Turma createTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public ResponseEntity<Turma> updateTurma(Long id, Turma turma) {
        if (!turmaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        turma.setId(id);
        return ResponseEntity.ok(turmaRepository.save(turma));
    }

    public ResponseEntity<Void> deleteTurma(Long id) {
        if (!turmaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        turmaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
