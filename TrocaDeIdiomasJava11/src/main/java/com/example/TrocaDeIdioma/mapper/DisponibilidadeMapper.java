package com.example.TrocaDeIdioma.mapper;

import com.example.TrocaDeIdioma.model.Disponibilidade;
import com.example.TrocaDeIdioma.model.Response.DisponibilidadeResponse;
import com.example.TrocaDeIdioma.model.Request.IncluirUsuarioRequest;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DisponibilidadeMapper {
    public static DisponibilidadeResponse toResponse(Disponibilidade entity) {
        return new ModelMapper().map(entity, DisponibilidadeResponse.class);
    }

  public static Disponibilidade toEntity(IncluirUsuarioRequest request) {
    return new ModelMapper().map(request, Disponibilidade.class);
  }

    public static List<DisponibilidadeResponse> toResponse(List<Disponibilidade> list) {
        return list.stream()
                .map(entity -> new ModelMapper().map(entity, DisponibilidadeResponse.class))
                .collect(Collectors.toList());
    }
}

