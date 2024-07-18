package com.example.TrocaDeIdioma.model;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Permissao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nome;

    public Permissao(String nome) {
        this.nome = nome;
    }

    public Permissao() {
    }
}
