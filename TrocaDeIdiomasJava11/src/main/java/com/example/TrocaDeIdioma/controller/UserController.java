package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.mapper.IncluirUsuarioMapper;
import com.example.TrocaDeIdioma.model.Permissao;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.Request.IncluirUsuarioRequest;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.repository.ProfessorRepository;
import com.example.TrocaDeIdioma.repository.UserRepository;
import com.example.TrocaDeIdioma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private ProfessorRepository professorRepository;
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody IncluirUsuarioRequest request) {
    if (userRepository.findByEmail(request.getEmail()) != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
    }
    request.setSenha(passwordEncoder.encode(request.getSenha()));

    User usuario = IncluirUsuarioMapper.toEntity(request);
    usuario.setPermissoes(Arrays.asList(new Permissao("USER")));

    usuario.setSaldo(BigDecimal.ZERO);

    userRepository.save(usuario);

    return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    // Busca o usuário pelo ID no banco de dados
    Optional<User> user = userRepository.findById(id);

    // Verifica se o usuário foi encontrado
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
    }
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    // Busca todos os usuários no banco de dados
    List<User> users = userRepository.findAll();

    return ResponseEntity.ok(users);
  }



  // outros métodos do controlador, como atualização e exclusão do usuário
}
