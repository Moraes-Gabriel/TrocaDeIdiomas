package com.example.TrocaDeIdioma.mapper;

import com.example.TrocaDeIdioma.model.Aula;
import com.example.TrocaDeIdioma.model.Disponibilidade;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.Response.AulaResponse;
import com.example.TrocaDeIdioma.model.Response.DisponibilidadeResponse;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import com.example.TrocaDeIdioma.model.Response.SolicitacaoAulaResponse;
import com.example.TrocaDeIdioma.model.SolicitacaoAula;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    TypeMap<Disponibilidade, DisponibilidadeResponse> disponibilidadeMap = modelMapper.createTypeMap(Disponibilidade.class, DisponibilidadeResponse.class);
    //disponibilidadeMap.addMapping(Disponibilidade::getUser, DisponibilidadeResponse::setUser);

    TypeMap<Professor, ProfessorResponse> professorMap = modelMapper.createTypeMap(Professor.class, ProfessorResponse.class);
    professorMap.addMapping(Professor::getDisponibilidade, ProfessorResponse::setDisponibilidade);

    TypeMap<SolicitacaoAula, SolicitacaoAulaResponse> solicitacaoAulaMap = modelMapper.createTypeMap(SolicitacaoAula.class, SolicitacaoAulaResponse.class);
    solicitacaoAulaMap.addMapping(SolicitacaoAula::getAluno, SolicitacaoAulaResponse::setAluno);
    solicitacaoAulaMap.addMapping(SolicitacaoAula::getProfessor, SolicitacaoAulaResponse::setProfessor);

    TypeMap<Aula, AulaResponse> aulaMap = modelMapper.createTypeMap(Aula.class, AulaResponse.class);
    aulaMap.addMapping(Aula::getProfessor, AulaResponse::setProfessor);
    aulaMap.addMapping(Aula::getAluno, AulaResponse::setAluno);
    return modelMapper;
  }
}
