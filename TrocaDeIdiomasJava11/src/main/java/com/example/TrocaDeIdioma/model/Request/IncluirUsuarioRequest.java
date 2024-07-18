package com.example.TrocaDeIdioma.model.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data
public class IncluirUsuarioRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    private List<String> idiomas;

    private BigDecimal valorPorHora;

}
