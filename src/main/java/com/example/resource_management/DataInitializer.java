package com.example.resource_management;

import com.example.resource_management.domain.Disciplina;
import com.example.resource_management.domain.Professor;
import com.example.resource_management.domain.Recurso;
import com.example.resource_management.domain.Reserva;
import com.example.resource_management.domain.Turma;
import com.example.resource_management.repository.DisciplinaRepository;
import com.example.resource_management.repository.ProfessorRepository;
import com.example.resource_management.repository.RecursoRepository;
import com.example.resource_management.repository.ReservaRepository;
import com.example.resource_management.repository.TurmaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final ReservaRepository reservaRepository;
    private final RecursoRepository recursoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public DataInitializer(ProfessorRepository professorRepository, 
                           TurmaRepository turmaRepository,
                           ReservaRepository reservaRepository,
                           RecursoRepository recursoRepository,
                           DisciplinaRepository disciplinaRepository) {
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.reservaRepository = reservaRepository;
        this.recursoRepository = recursoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Override
    public void run(String... args) {
        Professor[] professores = {
            new Professor("Professor 1", "prof1@example.com"),
            new Professor("Professor 2", "prof2@example.com"),
            new Professor("Professor 3", "prof3@example.com"),
            new Professor("Professor 4", "prof4@example.com"),
            new Professor("Professor 5", "prof5@example.com")
        };
        professorRepository.saveAll(Arrays.asList(professores));

        Turma[] turmas = {
            new Turma(101, "08:00 - 10:00", "Professor 1"),
            new Turma(102, "10:00 - 12:00", "Professor 2"),
            new Turma(103, "14:00 - 16:00", "Professor 3"),
            new Turma(104, "16:00 - 18:00", "Professor 4"),
            new Turma(105, "08:00 - 10:00", "Professor 5"),
            new Turma(106, "10:00 - 12:00", "Professor 1"),
            new Turma(107, "14:00 - 16:00", "Professor 2"),
            new Turma(108, "16:00 - 18:00", "Professor 3"),
            new Turma(109, "08:00 - 10:00", "Professor 4"),
            new Turma(110, "10:00 - 12:00", "Professor 5")
        };
        turmaRepository.saveAll(Arrays.asList(turmas));

        Reserva[] reservas = {
            new Reserva(LocalDate.now(), "08:00 - 10:00", 101),
            new Reserva(LocalDate.now(), "10:00 - 12:00", 102),
            new Reserva(LocalDate.now().plusDays(1), "14:00 - 16:00", 103),
            new Reserva(LocalDate.now().plusDays(2), "16:00 - 18:00", 104),
            new Reserva(LocalDate.now().plusDays(3), "08:00 - 10:00", 105)
        };
        reservaRepository.saveAll(Arrays.asList(reservas));

        Recurso[] recursos = {
            new Recurso(001, "Laboratório"),
            new Recurso(010, "Sala com Projetor"),
            new Recurso(011, "Sala com Projetor"),
            new Recurso(100, "Sala com Projetor"),
            new Recurso(101, "Laboratório")
        };
        recursoRepository.saveAll(Arrays.asList(recursos));

        Disciplina[] disciplinas = {
            new Disciplina("DISC101", "Matemática", 60),
            new Disciplina("DISC102", "Física", 45),
            new Disciplina("DISC103", "Química", 50),
            new Disciplina("DISC104", "Biologia", 30),
            new Disciplina("DISC105", "História", 40)
        };
        disciplinaRepository.saveAll(Arrays.asList(disciplinas));

        System.out.println("Banco de dados inicializado com dados de teste.");
    }

}
