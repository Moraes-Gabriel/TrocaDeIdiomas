package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.Disponibilidade;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User porId(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User não encontrado"));
  }
}
