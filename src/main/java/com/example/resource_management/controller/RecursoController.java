package com.example.resource_management.controller;

import com.example.resource_management.domain.Recurso;
import com.example.resource_management.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping
    public List<Recurso> getAllRecursos() {
        return recursoService.getAllRecursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> getRecursoById(@PathVariable Long id) {
        return recursoService.getRecursoById(id);
    }

    @PostMapping
    public Recurso createRecurso(@RequestBody Recurso recurso) {
        return recursoService.createRecurso(recurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> updateRecurso(@PathVariable Long id, @RequestBody Recurso recurso) {
        return recursoService.updateRecurso(id, recurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurso(@PathVariable Long id) {
        return recursoService.deleteRecurso(id);
    }

}
