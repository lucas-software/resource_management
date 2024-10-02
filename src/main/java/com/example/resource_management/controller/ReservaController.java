package com.example.resource_management.controller;

import com.example.resource_management.domain.Reserva;
import com.example.resource_management.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.getAllReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return reservaService.getReservaById(id);
    }

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaService.createReserva(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        return reservaService.updateReserva(id, reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        return reservaService.deleteReserva(id);
    }

}
