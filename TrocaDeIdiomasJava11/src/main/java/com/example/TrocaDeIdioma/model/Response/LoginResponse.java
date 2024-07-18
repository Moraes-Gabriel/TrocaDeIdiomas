package com.example.TrocaDeIdioma.model.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoginResponse {

    private Long usuarioId;
    private BigDecimal saldo;

    private String role;
}
