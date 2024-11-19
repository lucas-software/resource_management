package com.example.resource_management;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.example.resource_management.domain.Professor;
import com.example.resource_management.repository.ProfessorRepository;
import com.example.resource_management.service.ProfessorService;
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
public class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;

    @Test
    void testGetAllProfessores() {
        Professor professor1 = new Professor();
        Professor professor2 = new Professor();
        when(professorRepository.findAll()).thenReturn(Arrays.asList(professor1, professor2));

        List<Professor> result = professorService.getAllProfessores();

        assertEquals(2, result.size());
        assertTrue(result.contains(professor1));
        assertTrue(result.contains(professor2));
    }

    @Test
    void testGetProfessorById_ProfessorFound() {
        Long professorId = 1L;
        Professor professor = new Professor();
        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));

        ResponseEntity<Professor> response = professorService.getProfessorById(professorId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(professor, response.getBody());
    }

    @Test
    void testGetProfessorById_ProfessorNotFound() {
        Long professorId = 1L;
        when(professorRepository.findById(professorId)).thenReturn(Optional.empty());

        ResponseEntity<Professor> response = professorService.getProfessorById(professorId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateProfessor() {
        Professor professor = new Professor();
        when(professorRepository.save(professor)).thenReturn(professor);

        Professor createdProfessor = professorService.createProfessor(professor);

        assertEquals(professor, createdProfessor);
        verify(professorRepository, times(1)).save(professor);
    }

    @Test
    void testUpdateProfessor_ProfessorExists() {
        Long professorId = 1L;
        Professor professor = new Professor();
        when(professorRepository.existsById(professorId)).thenReturn(true);
        when(professorRepository.save(professor)).thenReturn(professor);

        ResponseEntity<Professor> response = professorService.updateProfessor(professorId, professor);

        assertEquals(OK, response.getStatusCode());
        assertEquals(professor, response.getBody());
    }

    @Test
    void testUpdateProfessor_ProfessorNotFound() {
        Long professorId = 1L;
        Professor professor = new Professor();
        when(professorRepository.existsById(professorId)).thenReturn(false);

        ResponseEntity<Professor> response = professorService.updateProfessor(professorId, professor);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(professorRepository, never()).save(professor);
    }

    @Test
    void testDeleteProfessor_ProfessorExists() {
        Long professorId = 1L;
        when(professorRepository.existsById(professorId)).thenReturn(true);

        ResponseEntity<Void> response = professorService.deleteProfessor(professorId);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(professorRepository, times(1)).deleteById(professorId);
    }

    @Test
    void testDeleteProfessor_ProfessorNotFound() {
        Long professorId = 1L;
        when(professorRepository.existsById(professorId)).thenReturn(false);

        ResponseEntity<Void> response = professorService.deleteProfessor(professorId);

        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(professorRepository, never()).deleteById(professorId);
    }
}
