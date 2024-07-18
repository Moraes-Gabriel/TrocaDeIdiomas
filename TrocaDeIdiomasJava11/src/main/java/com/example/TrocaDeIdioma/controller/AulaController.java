package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Response.AulaResponse;
import com.example.TrocaDeIdioma.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/aulas")
public class AulaController {

  @Autowired
  private AulaService aulaService;

  @PutMapping("/{id}/concluir/{rating}")
  public ResponseEntity<?> concluirAula(@PathVariable Long id, @PathVariable int rating) {
    aulaService.concluirAula(id, rating);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}/aluno/cancelar")
  public ResponseEntity<?> alunoCancelarAula(@PathVariable Long id) {
    aulaService.alunoCancelarAula(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}/professor/cancelar")
  public ResponseEntity<?> professorCancelarAula(@PathVariable Long id) {
    aulaService.professorCancelarAula(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  @GetMapping("/agendadas")
  public List<AulaResponse> getAllAulasAgendadas() {
    return aulaService.getAllAulasAgendadas();
  }

  @GetMapping("/concluidas")
  public List<AulaResponse> getAllAulasConcluidas() {
    return aulaService.getAllAulasConcluidas();
  }

  @GetMapping("/{id}/verificar/se/esta/permitido")
  public boolean verificarSeEstaPermitido(@PathVariable Long id) {
    return aulaService.verificarSeEstaPermitido(id);
  }

  @GetMapping("/encerradas")
  public List<AulaResponse> getAulasEncerradas() {
    return aulaService.getAulasEncerradas();
  }
}
