package com.example.resource_management.controller;

import com.example.resource_management.domain.Turma;
import com.example.resource_management.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public List<Turma> getAllTurmas() {
        return turmaService.getAllTurmas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable Long id) {
        return turmaService.getTurmaById(id);
    }

    @PostMapping
    public Turma createTurma(@RequestBody Turma turma) {
        return turmaService.createTurma(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@PathVariable Long id, @RequestBody Turma turma) {
        return turmaService.updateTurma(id, turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        return turmaService.deleteTurma(id);
    }

}
