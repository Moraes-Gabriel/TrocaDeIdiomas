package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.model.*;
import com.example.TrocaDeIdioma.model.Response.AulaResponse;
import com.example.TrocaDeIdioma.repository.AulaRepository;
import com.example.TrocaDeIdioma.repository.UserRepository;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AulaService {

  @Autowired
  private AulaRepository repository;

  @Autowired
  private ProfessorService professorService;

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;
  @Transactional
  public void alunoCancelarAula(Long aulaId) {

    Aula aula = porId(aulaId);
    User usuarioAutenticado = usuarioAutenticadoService.get();
    if (aula.getStatus() != StatusAula.AGENDADA) {
      throw new ResponseStatusException(BAD_REQUEST, "Não é possível cancelar uma aula que não está confirmada");
    }

    if(aula.getAluno().getId() != usuarioAutenticado.getId()){
      throw new ResponseStatusException(BAD_REQUEST, "Voce não tem permissao para fazer isso");
    }

    aula.setStatus(StatusAula.CANCELADA_ALUNO);

    Professor professor = professorService.porId(aula.getProfessor().getId());
    Aluno aluno = alunoService.porId(aula.getAluno().getId());

    professor.adicionarSaldo(aula.getValorDaAula().divide(new BigDecimal("2")).setScale(2));
    aluno.adicionarSaldo(aula.getValorDaAula().divide(new BigDecimal("2")).setScale(2));
    save(aula);

  }

  @Transactional
  public void professorCancelarAula(Long aulaId) {

    Aula aula = porId(aulaId);
    User usuarioAutenticado = usuarioAutenticadoService.get();

    if (aula.getStatus() != StatusAula.AGENDADA) {
      throw new ResponseStatusException(BAD_REQUEST, "Não é possível cancelar uma aula que não está confirmada");
    }
    if(aula.getProfessor().getId() != usuarioAutenticado.getId()){
      throw new ResponseStatusException(BAD_REQUEST, "Voce não tem permissao para fazer isso");
    }

    aula.setStatus(StatusAula.CANCELADA_PROFESSOR);
    aula.setCancelada(true);
    aula.setDataHoraCancelamento(LocalDateTime.now());

    Aluno aluno = alunoService.porId(aula.getAluno().getId());
    aluno.adicionarSaldo(aula.getValorDaAula());

    save(aula);
  }

  @Transactional
  public void concluirAula(Long aulaId, int nota) {

    Aula aula = porId(aulaId);

    if (aula.getStatus() != StatusAula.AGENDADA) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível concluir uma aula que não está confirmada");
    }

    aula.setStatus(StatusAula.CONCLUIDA);
    aula.setDataHoraFim(LocalDateTime.now());
    aula.setNota(nota);
    User professor = aula.getProfessor();
    BigDecimal valorDaAula = aula.getValorDaAula();

    professor.adicionarSaldo(valorDaAula);

    userRepository.save(professor);
    repository.save(aula);
  }
  public Aula save(Aula aula) {
    return repository.save(aula);
  }
  public Aula porId(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Aula não encontrado"));
  }

  public List<AulaResponse> getAllAulasAgendadas() {
    List<Aula> aulas = new ArrayList<>();
    if(usuarioAutenticadoService.get().getUsuarioTipo() == UsuarioTipo.PROFESSOR){
      aulas = repository.findAllByProfessorIdAndStatus(usuarioAutenticadoService.getId(), StatusAula.AGENDADA);
    }

    if(usuarioAutenticadoService.get().getUsuarioTipo() == UsuarioTipo.ALUNO){
      aulas = repository.findAllByAlunoIdAndStatus(usuarioAutenticadoService.getId(), StatusAula.AGENDADA);
    }

      return aulas.stream()
        .map(aula -> modelMapper.map(aula, AulaResponse.class)).collect(Collectors.toList());
  }

  public boolean verificarSeEstaPermitido(Long id) {
    Aula aula = porId(id);
    return aula.getAluno().getId().equals(usuarioAutenticadoService.getId()) || aula.getProfessor().getId().equals(usuarioAutenticadoService.getId()) && aula.getStatus() == StatusAula.AGENDADA;
  }

  public List<AulaResponse> getAulasEncerradas() {
    User user = usuarioAutenticadoService.get();
    LocalDateTime dataAtual = LocalDateTime.now();
    List<Aula> aulasEncerradas = repository.findAllByProfessorIdAndDataHoraFimBeforeAndStatusOrAlunoIdAndDataHoraFimBeforeAndStatus(user.getId(), dataAtual, StatusAula.AGENDADA, user.getId(), dataAtual, StatusAula.AGENDADA);
    return aulasEncerradas.stream()
      .map(aula -> modelMapper.map(aula, AulaResponse.class))
      .collect(Collectors.toList());
  }

  public List<AulaResponse> getAllAulasConcluidas() {
    Long id = usuarioAutenticadoService.getId();

    List<Aula> aulas = repository.findAllByProfessorIdAndStatusOrAlunoIdAndStatus(id, StatusAula.CONCLUIDA, id, StatusAula.CONCLUIDA);
    return aulas.stream()
      .map(aula -> modelMapper.map(aula, AulaResponse.class)).collect(Collectors.toList());
  }


}
