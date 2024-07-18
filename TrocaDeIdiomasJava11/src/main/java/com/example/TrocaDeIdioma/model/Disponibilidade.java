package com.example.TrocaDeIdioma.model;

import com.example.TrocaDeIdioma.util.GetDiaSemana;
import lombok.Data;
import org.hibernate.engine.jdbc.dialect.spi.DialectFactory;

import javax.persistence.*;
import java.text.Normalizer;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

@Entity
@Data
public class Disponibilidade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "professor_id")
  private Professor user;

  private LocalTime horaInicio;

  private LocalTime horaFim;

  @ElementCollection(targetClass = DiaSemana.class)
  @Enumerated(EnumType.STRING)
  private Set<DiaSemana> diasDisponiveis;


  public boolean isAvailable(LocalTime startTime, LocalTime endTime, DiaSemana dia) {
    if (!diasDisponiveis.contains(dia)) {
      return false; // O professor não está disponível nesse dia
    }

    if (startTime.isBefore(horaInicio) || endTime.isAfter(horaFim)) {
      return false; // O intervalo de tempo está fora dos limites de disponibilidade do professor
    }

    return true; // O professor está disponível no dia e horário especificados
  }

  public boolean isAvailable(LocalDateTime startTime) {
    DiaSemana diaSemana = GetDiaSemana.getDiaSemana(startTime.getDayOfWeek().toString());
    LocalDateTime comecoDoTempo = startTime.plusMinutes(1);
    if (!diasDisponiveis.contains(diaSemana)) {
      return false; // O professor não está disponível nesse dia
    }

    if (comecoDoTempo.toLocalTime().isBefore(horaInicio.plusMinutes(1)) || comecoDoTempo.toLocalTime().isAfter(horaFim)) {
      return false; // O intervalo de tempo está fora dos limites de disponibilidade do professor
    }

    return true; // O professor está disponível no dia e horário especificados
  }


}

