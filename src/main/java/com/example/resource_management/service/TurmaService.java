package com.example.resource_management.service;

import com.example.resource_management.domain.Turma;
import com.example.resource_management.repository.ProfessorRepository;
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

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public ResponseEntity<Turma> getTurmaById(Long id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        return turma.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public Turma createTurma(Turma turma) {
        if (isProfessorAvailable(turma)) {
            return turmaRepository.save(turma);
        } else {
            throw new IllegalArgumentException("Professor is not available at the given time.");
        }
    }

    public ResponseEntity<Turma> updateTurma(Long id, Turma turma) {
        if (!turmaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        turma.setId(id);
        if (isProfessorAvailable(turma)) {
            return ResponseEntity.ok(turmaRepository.save(turma));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    public ResponseEntity<Void> deleteTurma(Long id) {
        if (!turmaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        turmaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isProfessorAvailable(Turma turma) {
        List<Turma> turmas = turmaRepository.findByProfessorId(turma.getProfessor().getId());
        for (Turma t : turmas) {
            if (t.getId().equals(turma.getId())) {
                continue; // Skip the same turma
            }
            for (int dia : turma.getDias()) {
                for (String horario : turma.getHorario()) {
                    if (contains(t.getDias(), dia) && contains(t.getHorario(), horario)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(String[] array, String value) {
        for (String s : array) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}