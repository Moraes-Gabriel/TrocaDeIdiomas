package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.*;
import com.example.TrocaDeIdioma.model.Response.UserChatResponse;
import com.example.TrocaDeIdioma.service.AlunoService;
import com.example.TrocaDeIdioma.service.AulaService;
import com.example.TrocaDeIdioma.service.ProfessorService;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Controller
public class ChatController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Autowired
  private AulaService aulaService;

  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;
  @Autowired
  private ProfessorService professorService;

  @Autowired
  private AlunoService alunoService;

  @MessageMapping("aula/{idAula}")
  public Message sendMessage(@DestinationVariable Long idAula, @Valid Message message) {

      Aula aula = aulaService.porId(idAula);
      changeSenderAndReceiberToMessage(message, aula);
      Long usuarioRecebedor = message.getReceiver().getId();
      String destinatario = Long.toString(usuarioRecebedor);
      messagingTemplate.convertAndSendToUser(destinatario,"/aula/"+aula.getId(),message);
      return message;
  }

  public void changeSenderAndReceiberToMessage(Message message, Aula aula) {
    if(message.getUserTipo().equals("PROFESSOR")){
      message.setSender(new UserChatResponse(aula.getProfessor().getId(), aula.getProfessor().getFirstName(), aula.getProfessor().getLastName()));
      message.setReceiver(new UserChatResponse(aula.getAluno().getId(), aula.getAluno().getFirstName(), aula.getAluno().getLastName()));

    }
    else if (message.getUserTipo().equals("ALUNO")){
      message.setSender(new UserChatResponse(aula.getAluno().getId(), aula.getAluno().getFirstName(), aula.getAluno().getLastName()));
      message.setReceiver(new UserChatResponse(aula.getProfessor().getId(), aula.getProfessor().getFirstName(), aula.getProfessor().getLastName()));
    }
  }
}
