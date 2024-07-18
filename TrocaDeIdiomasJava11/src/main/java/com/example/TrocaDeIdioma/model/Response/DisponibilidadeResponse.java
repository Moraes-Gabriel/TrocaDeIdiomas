package com.example.TrocaDeIdioma.model.Response;

import com.example.TrocaDeIdioma.model.DiaSemana;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Data
public class DisponibilidadeResponse {

  private Long id;

  private LocalTime horaInicio;

  private LocalTime horaFim;

  private Set<DiaSemana> diasDisponiveis;
}
