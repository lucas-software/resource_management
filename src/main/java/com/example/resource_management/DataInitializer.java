package com.example.resource_management;

import com.example.resource_management.domain.Disciplina;
import com.example.resource_management.domain.Professor;
import com.example.resource_management.domain.Recurso;
import com.example.resource_management.domain.Alocacao;
import com.example.resource_management.domain.Turma;
import com.example.resource_management.repository.DisciplinaRepository;
import com.example.resource_management.repository.ProfessorRepository;
import com.example.resource_management.repository.RecursoRepository;
import com.example.resource_management.repository.AlocacaoRepository;
import com.example.resource_management.repository.TurmaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final AlocacaoRepository alocacaoRepository;
    private final RecursoRepository recursoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public DataInitializer(ProfessorRepository professorRepository, 
                           TurmaRepository turmaRepository,
                           AlocacaoRepository alocacaoRepository,
                           RecursoRepository recursoRepository,
                           DisciplinaRepository disciplinaRepository) {
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.alocacaoRepository = alocacaoRepository;
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

        Turma[] turmas = {
            new Turma(101, new String[] {"A","B"}, professores[0], new int[] {1,3}, disciplinas[0]),
            new Turma(102, new String[] {"C","D"}, professores[1], new int[] {2,4}, disciplinas[1]),
            new Turma(103, new String[] {"E","E1"}, professores[2], new int[] {3}, disciplinas[2]),
            new Turma(104, new String[] {"F","G"}, professores[3], new int[] {1,3}, disciplinas[3]),
            new Turma(105, new String[] {"H","I"}, professores[4], new int[] {2,4}, disciplinas[4]),
            new Turma(106, new String[] {"J","K"}, professores[0], new int[] {1}, disciplinas[0]),
            new Turma(107, new String[] {"L","M"}, professores[1], new int[] {2,4}, disciplinas[1]),
            new Turma(108, new String[] {"N","P"}, professores[2], new int[] {6}, disciplinas[2]),
            new Turma(109, new String[] {"A","B"}, professores[3], new int[] {1,3}, disciplinas[3]),
            new Turma(110, new String[] {"C","D"}, professores[4], new int[] {6}, disciplinas[4])
        };
        turmaRepository.saveAll(Arrays.asList(turmas));

        Alocacao[] alocacao = {
            new Alocacao(LocalDate.now(), new String[] {"A","B"}, new int[] {1}, turmas[0], recursos[0]), // adicionar um dia específico
            new Alocacao(LocalDate.now(), new String[] {"C","D"}, new int [] {2}, turmas[1], recursos[1]),
            new Alocacao(LocalDate.now().plusDays(1), new String[] {"E","E1"}, new int[] {3}, turmas[2], recursos[2]),
            new Alocacao(LocalDate.now().plusDays(2), new String[] {"F","G"}, new int[] {1,3}, turmas[3], recursos[3]),
            new Alocacao(LocalDate.now().plusDays(3), new String[] {"H","I"}, new int[] {2,4}, turmas[4], recursos[4]),
        };
        alocacaoRepository.saveAll(Arrays.asList(alocacao));

        System.out.println("Banco de dados inicializado com dados de teste.");
    }

}
