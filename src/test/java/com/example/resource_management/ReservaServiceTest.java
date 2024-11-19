package com.example.resource_management;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.example.resource_management.domain.Alocacao;
import com.example.resource_management.repository.AlocacaoRepository;
import com.example.resource_management.service.AlocacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    private AlocacaoRepository reservaRepository;

    @InjectMocks
    private AlocacaoService reservaService;

    @Test
    void testGetAllReservas() {
        Alocacao reserva1 = new Alocacao();
        Alocacao reserva2 = new Alocacao();
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reserva1, reserva2));

        List<Alocacao> result = reservaService.getAllAlocacao();

        assertEquals(2, result.size());
        assertTrue(result.contains(reserva1));
        assertTrue(result.contains(reserva2));
    }

    @Test
    void testGetReservaById_ReservaFound() {
        Long reservaId = 1L;
        Alocacao reserva = new Alocacao();
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

        ResponseEntity<Alocacao> response = reservaService.getAlocacaoById(reservaId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(reserva, response.getBody());
    }

    @Test
    void testGetReservaById_ReservaNotFound() {
        Long reservaId = 1L;
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());

        ResponseEntity<Alocacao> response = reservaService.getAlocacaoById(reservaId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateReserva() {
        Alocacao reserva = new Alocacao();
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        Alocacao createdReserva = reservaService.createAlocacao(reserva);

        assertEquals(reserva, createdReserva);
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    void testUpdateReserva_ReservaExists() {
        Long reservaId = 1L;
        Alocacao reserva = new Alocacao();
        when(reservaRepository.existsById(reservaId)).thenReturn(true);
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        ResponseEntity<Alocacao> response = reservaService.updateAlocacao(reservaId, reserva);

        assertEquals(OK, response.getStatusCode());
        assertEquals(reserva, response.getBody());
    }

    @Test
    void testUpdateReserva_ReservaNotFound() {
        Long reservaId = 1L;
        Alocacao reserva = new Alocacao();
        when(reservaRepository.existsById(reservaId)).thenReturn(false);

        ResponseEntity<Alocacao> response = reservaService.updateAlocacao(reservaId, reserva);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(reservaRepository, never()).save(reserva);
    }

    @Test
    void testDeleteReserva_ReservaExists() {
        Long reservaId = 1L;
        when(reservaRepository.existsById(reservaId)).thenReturn(true);

        ResponseEntity<Void> response = reservaService.deleteAlocacao(reservaId);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(reservaRepository, times(1)).deleteById(reservaId);
    }

    @Test
    void testDeleteReserva_ReservaNotFound() {
        Long reservaId = 1L;
        when(reservaRepository.existsById(reservaId)).thenReturn(false);

        ResponseEntity<Void> response = reservaService.deleteAlocacao(reservaId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(reservaRepository, never()).deleteById(reservaId);
    }
}
