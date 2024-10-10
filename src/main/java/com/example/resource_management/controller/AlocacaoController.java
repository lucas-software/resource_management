package com.example.resource_management.controller;

import com.example.resource_management.domain.Alocacao;
import com.example.resource_management.service.AlocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class AlocacaoController {

    @Autowired
    private AlocacaoService alocacaoService;

    @GetMapping
    public List<Alocacao> getAllAlocacao() {
        return alocacaoService.getAllAlocacao();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alocacao> getAlocacaoById(@PathVariable Long id) {
        return alocacaoService.getAlocacaoById(id);
    }

    @PostMapping
    public Alocacao createAlocacao(@RequestBody Alocacao alocacao) {
        return alocacaoService.createAlocacao(alocacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alocacao> updateAlocacao(@PathVariable Long id, @RequestBody Alocacao alocacao) {
        return alocacaoService.updateAlocacao(id, alocacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlocacao(@PathVariable Long id) {
        return alocacaoService.deleteAlocacao(id);
    }

}
