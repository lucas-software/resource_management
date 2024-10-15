package com.example.resource_management;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.example.resource_management.domain.Turma;
import com.example.resource_management.repository.TurmaRepository;
import com.example.resource_management.service.TurmaService;
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
public class TurmaServiceTest {

    @Mock
    private TurmaRepository turmaRepository;

    @InjectMocks
    private TurmaService turmaService;

    @Test
    void testGetAllTurmas() {
        Turma turma1 = new Turma();
        Turma turma2 = new Turma();
        when(turmaRepository.findAll()).thenReturn(Arrays.asList(turma1, turma2));

        List<Turma> result = turmaService.getAllTurmas();

        assertEquals(2, result.size());
        assertTrue(result.contains(turma1));
        assertTrue(result.contains(turma2));
    }

    @Test
    void testGetTurmaById_TurmaFound() {
        Long turmaId = 1L;
        Turma turma = new Turma();
        when(turmaRepository.findById(turmaId)).thenReturn(Optional.of(turma));

        ResponseEntity<Turma> response = turmaService.getTurmaById(turmaId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(turma, response.getBody());
    }

    @Test
    void testGetTurmaById_TurmaNotFound() {
        Long turmaId = 1L;
        when(turmaRepository.findById(turmaId)).thenReturn(Optional.empty());

        ResponseEntity<Turma> response = turmaService.getTurmaById(turmaId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateTurma() {
        Turma turma = new Turma();
        when(turmaRepository.save(turma)).thenReturn(turma);

        Turma createdTurma = turmaService.createTurma(turma);

        assertEquals(turma, createdTurma);
        verify(turmaRepository, times(1)).save(turma);
    }

    @Test
    void testUpdateTurma_TurmaExists() {
        Long turmaId = 1L;
        Turma turma = new Turma();
        when(turmaRepository.existsById(turmaId)).thenReturn(true);
        when(turmaRepository.save(turma)).thenReturn(turma);

        ResponseEntity<Turma> response = turmaService.updateTurma(turmaId, turma);

        assertEquals(OK, response.getStatusCode());
        assertEquals(turma, response.getBody());
    }

    @Test
    void testUpdateTurma_TurmaNotFound() {
        Long turmaId = 1L;
        Turma turma = new Turma();
        when(turmaRepository.existsById(turmaId)).thenReturn(false);

        ResponseEntity<Turma> response = turmaService.updateTurma(turmaId, turma);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(turmaRepository, never()).save(turma);
    }

    @Test
    void testDeleteTurma_TurmaExists() {
        Long turmaId = 1L;
        when(turmaRepository.existsById(turmaId)).thenReturn(true);

        ResponseEntity<Void> response = turmaService.deleteTurma(turmaId);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(turmaRepository, times(1)).deleteById(turmaId);
    }

    @Test
    void testDeleteTurma_TurmaNotFound() {
        Long turmaId = 1L;
        when(turmaRepository.existsById(turmaId)).thenReturn(false);

        ResponseEntity<Void> response = turmaService.deleteTurma(turmaId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(turmaRepository, never()).deleteById(turmaId);
    }
}
