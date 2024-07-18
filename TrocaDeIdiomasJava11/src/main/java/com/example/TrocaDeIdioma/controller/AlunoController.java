package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.mapper.ProfessorMapper;
import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.Permissao;
import com.example.TrocaDeIdioma.model.Request.IncluirAlunoRequest;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.model.UsuarioTipo;
import com.example.TrocaDeIdioma.repository.AlunoRepository;
import com.example.TrocaDeIdioma.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

  @Autowired
  private AlunoService alunoService;
  @Autowired
  private AlunoRepository alunoRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody IncluirAlunoRequest request) {
    if (alunoRepository.findByEmail(request.getEmail()) != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
    }

    request.setSenha(passwordEncoder.encode(request.getSenha()));

    Aluno usuario = new Aluno();
    usuario.setFirstName(request.getFirstName());
    usuario.setLastName(request.getLastName());
    usuario.setEmail(request.getEmail());
    usuario.setNivel(request.getNivel());
    usuario.setSenha(request.getSenha());
    usuario.setUsuarioTipo(UsuarioTipo.ALUNO);
    usuario.setPermissoes(Arrays.asList(new Permissao("USER"), new Permissao("ALUNO")));

    usuario.setSaldo(BigDecimal.ZERO);

    alunoRepository.save(usuario);

    return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
  }
}
