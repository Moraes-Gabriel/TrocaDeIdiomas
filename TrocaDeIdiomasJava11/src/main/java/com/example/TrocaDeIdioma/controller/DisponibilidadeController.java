package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Disponibilidade;
import com.example.TrocaDeIdioma.model.Request.DisponibilidadeRequest;
import com.example.TrocaDeIdioma.model.Response.DisponibilidadeResponse;
import com.example.TrocaDeIdioma.service.DisponibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadeController {

  @Autowired
  private DisponibilidadeService disponibilidadeService;

  @PostMapping
  @Secured("ROLE_PROFESSOR")
  public ResponseEntity<Disponibilidade> save(@RequestBody Disponibilidade disponibilidade) {
    Disponibilidade result = disponibilidadeService.save(disponibilidade);
    return ResponseEntity.ok(result);
  }

  @GetMapping
  public List<DisponibilidadeResponse> findAll() {
    return disponibilidadeService.findAll();
  }
  @GetMapping("/entre")
  public List<DisponibilidadeResponse> findAllBetweenHours() {
    return disponibilidadeService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Disponibilidade> findById(@PathVariable Long id) {
    Disponibilidade disponibilidade = disponibilidadeService.findById(id);
    return ResponseEntity.ok(disponibilidade);
  }

  @PutMapping()
  @Secured("ROLE_PROFESSOR")
  public void update(@RequestBody DisponibilidadeRequest disponibilidade) {
    disponibilidadeService.update(disponibilidade);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    disponibilidadeService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
