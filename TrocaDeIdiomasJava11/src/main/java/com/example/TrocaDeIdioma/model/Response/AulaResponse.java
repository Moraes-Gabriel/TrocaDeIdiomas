package com.example.TrocaDeIdioma.model.Response;

import java.time.LocalDateTime;

import com.example.TrocaDeIdioma.model.Professor;
import lombok.Data;

@Data
public class AulaResponse {

  private Long id;

  private ProfessorResponse professor;
  private AlunoResponse aluno;
  private int nota;
  private LocalDateTime dataHoraInicio;

  private LocalDateTime dataHoraFim;

  private String idioma;

  private String status;
}
