package com.example.TrocaDeIdioma.repository;


import com.example.TrocaDeIdioma.model.SolicitacaoAula;
import com.example.TrocaDeIdioma.model.StatusSolicitacaoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoAulaRepository extends JpaRepository<SolicitacaoAula, Long> {

  List<SolicitacaoAula> findAllByProfessorIdAndStatus(Long professorId, StatusSolicitacaoAula status);

    List<SolicitacaoAula> findAllByAlunoIdAndStatus(Long id, StatusSolicitacaoAula pendente);
}
