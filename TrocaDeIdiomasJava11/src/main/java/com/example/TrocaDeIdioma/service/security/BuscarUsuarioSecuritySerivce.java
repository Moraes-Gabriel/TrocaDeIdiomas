package com.example.TrocaDeIdioma.service.security;

import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.repository.AlunoRepository;
import com.example.TrocaDeIdioma.repository.ProfessorRepository;
import com.example.TrocaDeIdioma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioSecuritySerivce implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Professor professor = professorRepository.findByEmail(email);
        if (professor != null) {
          return new UsuarioSecurity( professor);
        }
        Aluno aluno = alunoRepository.findByEmail(email);
        if (aluno != null) {
          return new UsuarioSecurity( aluno);
        }
        return new UsuarioSecurity(null);
    }
}
