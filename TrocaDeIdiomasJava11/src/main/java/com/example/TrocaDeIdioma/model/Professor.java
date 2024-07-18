package com.example.TrocaDeIdioma.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Builder
@Data

public class Professor extends User {
  @ElementCollection(targetClass = String.class)
  @CollectionTable(name = "professor_idiomas", joinColumns = @JoinColumn(name = "professor_id"))
  @Column(name = "idioma")
  private List<String> idiomas;

  @Column(name = "valor_por_hora")
  private BigDecimal valorPorHora;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
  private Disponibilidade disponibilidade;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
  private List<SolicitacaoAula> solicitacoesRecebidasAulas;

  @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
  private List<Aula> aulasRealizadas;

  public void adicionarAula(Aula aula) {
    aulasRealizadas.add(aula);
  }
  public void adicionarSolicitacaoAula(SolicitacaoAula solicitacaoAula) {
    solicitacoesRecebidasAulas.add(solicitacaoAula);
  }

  public void removerSolicitacaoAula(SolicitacaoAula solicitacao) {
    solicitacoesRecebidasAulas.remove(solicitacao);
  }


}
