package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.mapper.ProfessorMapper;
import com.example.TrocaDeIdioma.model.*;
import com.example.TrocaDeIdioma.model.Request.FindAvaliableProfessorRequest;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import com.example.TrocaDeIdioma.model.Response.SolicitacaoAulaResponse;
import com.example.TrocaDeIdioma.repository.AlunoRepository;
import com.example.TrocaDeIdioma.repository.ProfessorRepository;
import com.example.TrocaDeIdioma.repository.SolicitacaoAulaRepository;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import com.example.TrocaDeIdioma.util.GetDiaSemana;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Status;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProfessorService {

  @Autowired
  private ProfessorRepository repository;

  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ProfessorMapper professorMapper;

  @Autowired
  private SolicitacaoAulaRepository solicitacaoAulaRepository;

  public Professor porId(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Professor não encontrado"));
  }

  public Professor professorAutenticado() {
    return repository.findById(usuarioAutenticadoService.getId())
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Professor não encontrado"));
  }
  public void salvar(Professor professor) {
    repository.save(professor);
  }

  public List<ProfessorResponse> findAvailableProfsByLanguage(String language, LocalTime dataHoraInicio, LocalTime dataHoraFim, LocalDate dia) {

    DiaSemana diaSemana = GetDiaSemana.getDiaSemana(dia.getDayOfWeek().toString());
    List<Professor> availableProfs = new ArrayList<>();

    List<Professor> professors = repository.findByIdiomas(language);

    for (Professor prof : professors) {
      Disponibilidade disponibilidade = prof.getDisponibilidade();

      if (disponibilidade != null && disponibilidade.isAvailable(dataHoraInicio,dataHoraFim, diaSemana)) {
        availableProfs.add(prof);
      }
    }

    return professorMapper.toResponse(availableProfs);
  }




}
