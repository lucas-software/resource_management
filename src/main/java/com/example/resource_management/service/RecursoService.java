package com.example.resource_management.service;

import com.example.resource_management.domain.Recurso;
import com.example.resource_management.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> getAllRecursos() {
        return recursoRepository.findAll();
    }

    public ResponseEntity<Recurso> getRecursoById(Long id) {
        Optional<Recurso> recurso = recursoRepository.findById(id);
        return recurso.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public Recurso createRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public ResponseEntity<Recurso> updateRecurso(Long id, Recurso recurso) {
        if (!recursoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        recurso.setId(id);
        return ResponseEntity.ok(recursoRepository.save(recurso));
    }

    public ResponseEntity<Void> deleteRecurso(Long id) {
        if (!recursoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        recursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
