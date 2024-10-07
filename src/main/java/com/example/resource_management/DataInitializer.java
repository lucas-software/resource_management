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
            new Turma(101, new String[] {"A","B"}, "Professor 1", null),
            new Turma(102, new String[] {"C","D"}, "Professor 2", null),
            new Turma(103, new String[] {"E","E1"}, "Professor 3", null),
            new Turma(104, new String[] {"F","G"}, "Professor 4", null),
            new Turma(105, new String[] {"H","I"}, "Professor 5", null),
            new Turma(106, new String[] {"J","K"}, "Professor 1", null),
            new Turma(107, new String[] {"L","M"}, "Professor 2", null),
            new Turma(108, new String[] {"N","P"}, "Professor 3", null),
            new Turma(109, new String[] {"A","B"}, "Professor 4", null),
            new Turma(110, new String[] {"C","D"}, "Professor 5", null)
        };
        turmaRepository.saveAll(Arrays.asList(turmas));

        Reserva[] reservas = {
            new Reserva(LocalDate.now(), new String[] {"A","B"}, 101),
            new Reserva(LocalDate.now(), new String[] {"C","D"}, 102),
            new Reserva(LocalDate.now().plusDays(1), new String[] {"E","E1"}, 103),
            new Reserva(LocalDate.now().plusDays(2), new String[] {"F","G"}, 104),
            new Reserva(LocalDate.now().plusDays(3), new String[] {"H","I"}, 105)
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
