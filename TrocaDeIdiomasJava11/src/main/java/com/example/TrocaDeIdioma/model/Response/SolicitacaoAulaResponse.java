package com.example.TrocaDeIdioma.model.Response;

import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.StatusSolicitacaoAula;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SolicitacaoAulaResponse {

  private Long id;

  private AlunoResponse aluno;
  private ProfessorResponse professor;

  private LocalDateTime dataHoraInicio;

  private LocalDateTime dataHoraFim;

  private String idioma;
  private Long nivel;

  private BigDecimal valorAula;

  private StatusSolicitacaoAula status;
}
