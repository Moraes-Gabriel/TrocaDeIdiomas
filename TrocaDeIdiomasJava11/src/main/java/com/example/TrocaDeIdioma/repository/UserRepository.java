package com.example.TrocaDeIdioma.repository;

import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  //@Query("SELECT p FROM Professor p WHERE :idioma IN p.idiomas")
  //List<Professor> findByIdiomas(@Param("idioma") String idioma);
}
