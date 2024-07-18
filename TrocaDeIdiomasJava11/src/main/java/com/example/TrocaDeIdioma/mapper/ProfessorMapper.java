package com.example.TrocaDeIdioma.mapper;

import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.Request.IncluirUsuarioRequest;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorMapper {

  @Autowired
  private ModelMapper modelMapper;

    public ProfessorResponse toResponse(Professor entity) {
        return new ModelMapper().map(entity, ProfessorResponse.class);
    }

  public static Professor toEntity(IncluirUsuarioRequest request) {
    return new ModelMapper().map(request, Professor.class);
  }

    public List<ProfessorResponse> toResponse(List<Professor> list) {
        return list.stream()
                .map(entity -> modelMapper.map(entity, ProfessorResponse.class))
                .collect(Collectors.toList());
    }
}

