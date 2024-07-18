package com.example.TrocaDeIdioma.repository;

import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

  List<Professor> findByIdiomas(String language);


  Professor findByEmail(String email);
}
