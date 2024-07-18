package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Request.SolicitacaoAulaRequest;
import com.example.TrocaDeIdioma.model.Response.SolicitacaoAulaResponse;
import com.example.TrocaDeIdioma.service.SolicitacaoAulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/solicitacoes")
public class SolicitacaoAulaController {

  @Autowired
  private SolicitacaoAulaService solicitacaoAulaService;

  @PostMapping("/aula")
  @Secured("ROLE_ALUNO")
  public void solicitarAula(@RequestBody SolicitacaoAulaRequest request) {
    solicitacaoAulaService.solicitarAula(request);
  }

  @PutMapping("/{id}/aceitar")
  @Secured("ROLE_PROFESSOR")
  public ResponseEntity<?> aceitarSolicitacao(@PathVariable Long id) {
    solicitacaoAulaService.aceitarSolicitacao(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/recusar")
  @Secured("ROLE_PROFESSOR")
  public ResponseEntity<?> recusarSolicitacao(@PathVariable Long id) {
    solicitacaoAulaService.recusarSolicitacao(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/recebidas")
  @Secured("ROLE_PROFESSOR")
  public List<SolicitacaoAulaResponse> getAllSolicitacoesRecebidasAulas() {
    return solicitacaoAulaService.getAllSolicitacoesRecebidas();
  }

  @GetMapping("/enviadas")
  @Secured("ROLE_ALUNO")
  public List<SolicitacaoAulaResponse> getAllSolicitacoesEnviadas() {
    return solicitacaoAulaService.getAllSolicitacoesEnviadas();
  }

  @PutMapping("/{id}/cancelar")
  public void CancelarSolicitacaoAula(@PathVariable Long id) {
    solicitacaoAulaService.cancelarSolicitacaoAula(id);
  }


}
