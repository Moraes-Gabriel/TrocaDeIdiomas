package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.mapper.ProfessorMapper;
import com.example.TrocaDeIdioma.model.*;
import com.example.TrocaDeIdioma.model.Request.IncluirUsuarioRequest;
import com.example.TrocaDeIdioma.model.Request.FindAvaliableProfessorRequest;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import com.example.TrocaDeIdioma.repository.ProfessorRepository;
import com.example.TrocaDeIdioma.service.AlunoService;
import com.example.TrocaDeIdioma.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

  @Autowired
  private ProfessorService professorService;

  @Autowired
  private ProfessorRepository professorRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AlunoService alunoService;
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody IncluirUsuarioRequest request) {
    if (professorRepository.findByEmail(request.getEmail()) != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
    }

    request.setSenha(passwordEncoder.encode(request.getSenha()));

    Professor usuario = ProfessorMapper.toEntity(request);
    usuario.setPermissoes(Arrays.asList(new Permissao("USER"), new Permissao("PROFESSOR")));

    usuario.setSaldo(BigDecimal.ZERO);
    usuario.setUsuarioTipo(UsuarioTipo.PROFESSOR);
    professorRepository.save(usuario);

    return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
  }

  @GetMapping("available/{language}")
  public List<ProfessorResponse> findAvailableUsersByLanguage(@PathVariable String language, @RequestParam String dataHoraInicio, @RequestParam String dataHoraFim, @RequestParam String dia) {
    LocalDate date = LocalDate.parse(dia);
    LocalTime start = LocalTime.parse(dataHoraInicio);
    LocalTime end = LocalTime.parse(dataHoraFim);
    return professorService.findAvailableProfsByLanguage(language, start, end, date);

  }




}
