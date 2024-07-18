package com.example.TrocaDeIdioma.model;

public enum DiaSemana {
  Segunda_feira("Monday"),
  Terca_feira("Tuesday"),
  Quarta_feira("Wednesday"),
  Quinta_feira("Thursday"),
  Sexta_feira("Friday"),
  Sabado("Saturday"),
  Domingo("Sunday");

  private String descricao;

  private DiaSemana(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  private DiaSemana getDiaSemana(String dia) {
    for (DiaSemana diaSemana : DiaSemana.values()) {
      if (diaSemana.getDescricao().equalsIgnoreCase(dia)) {
        return diaSemana;
      }
    }
    throw new IllegalArgumentException("Dia da semana inválido: " + dia);
  }
}
