package com.example.TrocaDeIdioma.model.Request;

import com.example.TrocaDeIdioma.model.DiaSemana;
import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
public class DisponibilidadeRequest {

  private LocalTime horaInicio;

  private LocalTime horaFim;

  private Set<DiaSemana> diasDisponiveis;
}
