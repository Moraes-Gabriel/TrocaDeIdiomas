package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.mapper.DisponibilidadeMapper;
import com.example.TrocaDeIdioma.model.Request.DisponibilidadeRequest;
import com.example.TrocaDeIdioma.model.Response.DisponibilidadeResponse;
import com.example.TrocaDeIdioma.model.Disponibilidade;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.repository.DisponibilidadeRepository;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisponibilidadeService {

  @Autowired
  private ProfessorService professorService;

  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;

  private final DisponibilidadeRepository disponibilidadeRepository;


  @Autowired
  private ModelMapper modelMapper;
  public DisponibilidadeService(DisponibilidadeRepository disponibilidadeRepository) {
    this.disponibilidadeRepository = disponibilidadeRepository;
  }

  public Disponibilidade save(Disponibilidade disponibilidade) {
    Professor professor = professorService.professorAutenticado();

    disponibilidade.setUser(professor);

    return disponibilidadeRepository.save(disponibilidade);
  }

  public List<DisponibilidadeResponse> findAll() {
    List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();
    return  disponibilidades.stream()
      .map(disponibilidade -> modelMapper.map(disponibilidade, DisponibilidadeResponse.class))
      .collect(Collectors.toList());
  }

  public Disponibilidade findById(Long id) {
    Optional<Disponibilidade> optional = disponibilidadeRepository.findById(id);
    return optional.get();
  }

  public void deleteById(Long id) {
    disponibilidadeRepository.deleteById(id);
  }


  public void update(DisponibilidadeRequest disponibilidade) {

    Professor professor = professorService.professorAutenticado();

    professor.getDisponibilidade().setHoraInicio(disponibilidade.getHoraInicio());
    professor.getDisponibilidade().setHoraFim(disponibilidade.getHoraFim());
    professor.getDisponibilidade().setDiasDisponiveis(disponibilidade.getDiasDisponiveis());
    disponibilidadeRepository.save(professor.getDisponibilidade());
  }
}
