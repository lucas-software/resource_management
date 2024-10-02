package com.example.resource_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.resource_management.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}