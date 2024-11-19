package com.example.resource_management;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.example.resource_management.domain.Disciplina;
import com.example.resource_management.repository.DisciplinaRepository;
import com.example.resource_management.service.DisciplinaService;
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
public class DisciplinaServiceTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private DisciplinaService disciplinaService;

    @Test
    void testGetAllDisciplinas() {
        Disciplina disciplina1 = new Disciplina();
        Disciplina disciplina2 = new Disciplina();
        when(disciplinaRepository.findAll()).thenReturn(Arrays.asList(disciplina1, disciplina2));

        List<Disciplina> result = disciplinaService.getAllDisciplinas();

        assertEquals(2, result.size());
        assertTrue(result.contains(disciplina1));
        assertTrue(result.contains(disciplina2));
    }

    @Test
    void testGetDisciplinaById_DisciplinaFound() {
        Long disciplinaId = 1L;
        Disciplina disciplina = new Disciplina();
        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina));

        ResponseEntity<Disciplina> response = disciplinaService.getDisciplinaById(disciplinaId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(disciplina, response.getBody());
    }

    @Test
    void testGetDisciplinaById_DisciplinaNotFound() {
        Long disciplinaId = 1L;
        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.empty());

        ResponseEntity<Disciplina> response = disciplinaService.getDisciplinaById(disciplinaId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateDisciplina() {
        Disciplina disciplina = new Disciplina();
        when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);

        Disciplina createdDisciplina = disciplinaService.createDisciplina(disciplina);

        assertEquals(disciplina, createdDisciplina);
        verify(disciplinaRepository, times(1)).save(disciplina);
    }

    @Test
    void testUpdateDisciplina_DisciplinaExists() {
        Long disciplinaId = 1L;
        Disciplina disciplina = new Disciplina();
        when(disciplinaRepository.existsById(disciplinaId)).thenReturn(true);
        when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);

        ResponseEntity<Disciplina> response = disciplinaService.updateDisciplina(disciplinaId, disciplina);

        assertEquals(OK, response.getStatusCode());
        assertEquals(disciplina, response.getBody());
    }

    @Test
    void testUpdateDisciplina_DisciplinaNotFound() {
        Long disciplinaId = 1L;
        Disciplina disciplina = new Disciplina();
        when(disciplinaRepository.existsById(disciplinaId)).thenReturn(false);

        ResponseEntity<Disciplina> response = disciplinaService.updateDisciplina(disciplinaId, disciplina);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(disciplinaRepository, never()).save(disciplina);
    }

    @Test
    void testDeleteDisciplina_DisciplinaExists() {
        Long disciplinaId = 1L;
        when(disciplinaRepository.existsById(disciplinaId)).thenReturn(true);

        ResponseEntity<Void> response = disciplinaService.deleteDisciplina(disciplinaId);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(disciplinaRepository, times(1)).deleteById(disciplinaId);
    }

    @Test
    void testDeleteDisciplina_DisciplinaNotFound() {
        Long disciplinaId = 1L;
        when(disciplinaRepository.existsById(disciplinaId)).thenReturn(false);

        ResponseEntity<Void> response = disciplinaService.deleteDisciplina(disciplinaId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(disciplinaRepository, never()).deleteById(disciplinaId);
    }
}
