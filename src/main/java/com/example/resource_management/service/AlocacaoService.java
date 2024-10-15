package com.example.resource_management.service;

import com.example.resource_management.domain.Alocacao;
import com.example.resource_management.repository.AlocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlocacaoService {

    @Autowired
    private AlocacaoRepository alocacaoRepository;

    public List<Alocacao> getAllAlocacao() {
        return alocacaoRepository.findAll();
    }

    public ResponseEntity<Alocacao> getAlocacaoById(Long id) {
        Optional<Alocacao> reserva = alocacaoRepository.findById(id);
        return reserva.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public Alocacao createAlocacao(Alocacao reserva) {
        return alocacaoRepository.save(reserva);
    }

    public ResponseEntity<Alocacao> updateAlocacao(Long id, Alocacao alocacao) {
        if (!alocacaoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        alocacao.setId(id);
        return ResponseEntity.ok(alocacaoRepository.save(alocacao));
    }

    public ResponseEntity<Void> deleteAlocacao(Long id) {
        if (!alocacaoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        alocacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
