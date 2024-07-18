package com.example.TrocaDeIdioma.model;

import javax.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(unique = true)
  private String email;

  @Column(name = "tipo")
  @Enumerated(EnumType.STRING)
  private UsuarioTipo usuarioTipo;

  @Column
  private String senha;

  @Column(name = "saldo")
  private BigDecimal saldo;


  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private List<Permissao> permissoes;


  public void adicionarSaldo(BigDecimal valor) {
    setSaldo(getSaldo().add(valor));
  }

  public void debitarSaldo(BigDecimal valorDaAula) {
    setSaldo(getSaldo().subtract(valorDaAula));
  }
}
