package com.example.resource_management.controller;

import com.example.resource_management.domain.Disciplina;
import com.example.resource_management.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaService.getAllDisciplinas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable Long id) {
        return disciplinaService.getDisciplinaById(id);
    }

    @PostMapping
    public Disciplina createDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaService.createDisciplina(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        return disciplinaService.updateDisciplina(id, disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
        return disciplinaService.deleteDisciplina(id);
    }

}
