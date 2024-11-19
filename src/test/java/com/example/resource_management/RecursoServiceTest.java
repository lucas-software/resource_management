package com.example.resource_management;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.example.resource_management.domain.Recurso;
import com.example.resource_management.repository.RecursoRepository;
import com.example.resource_management.service.RecursoService;
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
public class RecursoServiceTest {

    @Mock
    private RecursoRepository recursoRepository;

    @InjectMocks
    private RecursoService recursoService;

    @Test
    void testGetAllRecursos() {
        Recurso recurso1 = new Recurso();
        Recurso recurso2 = new Recurso();
        when(recursoRepository.findAll()).thenReturn(Arrays.asList(recurso1, recurso2));

        List<Recurso> result = recursoService.getAllRecursos();

        assertEquals(2, result.size());
        assertTrue(result.contains(recurso1));
        assertTrue(result.contains(recurso2));
    }

    @Test
    void testGetRecursoById_RecursoFound() {
        Long recursoId = 1L;
        Recurso recurso = new Recurso();
        when(recursoRepository.findById(recursoId)).thenReturn(Optional.of(recurso));

        ResponseEntity<Recurso> response = recursoService.getRecursoById(recursoId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(recurso, response.getBody());
    }

    @Test
    void testGetRecursoById_RecursoNotFound() {
        Long recursoId = 1L;
        when(recursoRepository.findById(recursoId)).thenReturn(Optional.empty());

        ResponseEntity<Recurso> response = recursoService.getRecursoById(recursoId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateRecurso() {
        Recurso recurso = new Recurso();
        when(recursoRepository.save(recurso)).thenReturn(recurso);

        Recurso createdRecurso = recursoService.createRecurso(recurso);

        assertEquals(recurso, createdRecurso);
        verify(recursoRepository, times(1)).save(recurso);
    }

    @Test
    void testUpdateRecurso_RecursoExists() {
        Long recursoId = 1L;
        Recurso recurso = new Recurso();
        when(recursoRepository.existsById(recursoId)).thenReturn(true);
        when(recursoRepository.save(recurso)).thenReturn(recurso);

        ResponseEntity<Recurso> response = recursoService.updateRecurso(recursoId, recurso);

        assertEquals(OK, response.getStatusCode());
        assertEquals(recurso, response.getBody());
    }

    @Test
    void testUpdateRecurso_RecursoNotFound() {
        Long recursoId = 1L;
        Recurso recurso = new Recurso();
        when(recursoRepository.existsById(recursoId)).thenReturn(false);

        ResponseEntity<Recurso> response = recursoService.updateRecurso(recursoId, recurso);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(recursoRepository, never()).save(recurso);
    }

    @Test
    void testDeleteRecurso_RecursoExists() {
        Long recursoId = 1L;
        when(recursoRepository.existsById(recursoId)).thenReturn(true);

        ResponseEntity<Void> response = recursoService.deleteRecurso(recursoId);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(recursoRepository, times(1)).deleteById(recursoId);
    }

    @Test
    void testDeleteRecurso_RecursoNotFound() {
        Long recursoId = 1L;
        when(recursoRepository.existsById(recursoId)).thenReturn(false);

        ResponseEntity<Void> response = recursoService.deleteRecurso(recursoId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(recursoRepository, never()).deleteById(recursoId);
    }
}

