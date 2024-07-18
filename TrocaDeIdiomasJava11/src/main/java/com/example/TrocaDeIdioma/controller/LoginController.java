package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Permissao;
import com.example.TrocaDeIdioma.model.Response.LoginResponse;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;

  @PostMapping()
  public LoginResponse login() {
    LoginResponse response = new LoginResponse();

    response.setUsuarioId(usuarioAutenticadoService.getId());
    response.setSaldo(usuarioAutenticadoService.get().getSaldo());
    List<Permissao> roles = usuarioAutenticadoService.get().getPermissoes();
    response.setRole(roles.get(1).getNome());
    return response;
  }
}
