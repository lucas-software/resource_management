package com.example.resource_management.service;

import com.example.resource_management.domain.Reserva;
import com.example.resource_management.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public ResponseEntity<Reserva> getReservaById(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return reserva.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public ResponseEntity<Reserva> updateReserva(Long id, Reserva reserva) {
        if (!reservaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        reserva.setId(id);
        return ResponseEntity.ok(reservaRepository.save(reserva));
    }

    public ResponseEntity<Void> deleteReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        reservaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
