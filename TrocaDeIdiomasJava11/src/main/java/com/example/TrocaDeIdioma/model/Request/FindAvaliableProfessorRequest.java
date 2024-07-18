package com.example.TrocaDeIdioma.model.Request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class FindAvaliableProfessorRequest {

  LocalTime dataHoraInicio;
  LocalTime dataHoraFim;
  LocalDate dia;

}
