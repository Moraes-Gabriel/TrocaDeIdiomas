package com.example.TrocaDeIdioma.mapper;


import com.example.TrocaDeIdioma.model.Request.IncluirUsuarioRequest;
import com.example.TrocaDeIdioma.model.User;
import org.modelmapper.ModelMapper;

public class IncluirUsuarioMapper {

    public static User toEntity(IncluirUsuarioRequest request) {
        return new ModelMapper().map(request, User.class);
    }
}
