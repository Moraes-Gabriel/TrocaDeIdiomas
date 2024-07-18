package com.example.TrocaDeIdioma.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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

public class Aluno extends User {

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno")
  private List<SolicitacaoAula> solicitacoesEnviadasAulas;

  @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
  private List<Aula> aulasRealizadas;
  @Column()
  private Long nivel;

  public void adicionarAula(Aula aula) {
    aulasRealizadas.add(aula);
  }
  public void adicionarSolicitacaoAula(SolicitacaoAula solicitacaoAula) {
    solicitacoesEnviadasAulas.add(solicitacaoAula);
  }


  public void removerSolicitacaoAula(SolicitacaoAula solicitacao) {
    solicitacoesEnviadasAulas.remove(solicitacao);
  }

}
