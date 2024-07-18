package com.example.TrocaDeIdioma.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Builder
@Data
public class Aula {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "professor_id")
  private Professor professor;

  @ManyToOne
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;

  @Column
  private String idioma;

  @Column()
  @Min(value = 1, message = "A nota deve ser no mínimo 1")
  @Max(value = 4, message = "A nota deve ser no máximo 4")
  private Integer nota;
  @Column(name = "data_hora_inicio")
  private LocalDateTime dataHoraInicio;

  @Column(name = "data_hora_fim")
  private LocalDateTime dataHoraFim;

  @Column(name = "valor_hora")
  private BigDecimal valorDaAula;

  @Column()
  private Long nivel;

  @Column(name = "status_aula")
  @Enumerated(EnumType.STRING)
  private StatusAula status;
  @Column(name = "data_hora_cancelamento")
  private LocalDateTime dataHoraCancelamento;

  @Column(name = "cancelada")
  private Boolean cancelada;


  // getters e setters
}


