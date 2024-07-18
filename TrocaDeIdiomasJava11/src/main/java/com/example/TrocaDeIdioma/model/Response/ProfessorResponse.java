package com.example.TrocaDeIdioma.model.Response;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProfessorResponse {

  private Long id;

  private String firstName;

  private String lastName;
  private List<String> idiomas;

  private BigDecimal valorPorHora;

  private DisponibilidadeResponse disponibilidade;

}
