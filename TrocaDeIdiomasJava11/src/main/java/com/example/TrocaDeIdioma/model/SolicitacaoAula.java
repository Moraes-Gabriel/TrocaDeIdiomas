package com.example.TrocaDeIdioma.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;
@Entity
@Data
public class SolicitacaoAula {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "professor_id")
  private Professor professor;

  @ManyToOne
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;

  @Column(name = "data_hora_inicio")
  private LocalDateTime dataHoraInicio;

  @Column(name = "data_hora_fim")
  private LocalDateTime dataHoraFim;

  @Column()
  private String idioma;
  @Column()
  private Long nivel;

  @Column(name = "valor_aula")
  private BigDecimal valorAula;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusSolicitacaoAula status;

}

