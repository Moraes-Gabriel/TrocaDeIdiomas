package com.example.TrocaDeIdioma.util;

import com.example.TrocaDeIdioma.model.DiaSemana;

public class GetDiaSemana {

  public static DiaSemana getDiaSemana(String dia) {
    for (DiaSemana diaSemana : DiaSemana.values()) {
      if (diaSemana.getDescricao().equalsIgnoreCase(dia)) {
        return diaSemana;
      }
    }
    throw new IllegalArgumentException("Dia da semana inválido: " + dia);
  }
}
