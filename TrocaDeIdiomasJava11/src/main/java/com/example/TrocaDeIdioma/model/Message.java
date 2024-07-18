package com.example.TrocaDeIdioma.model;

import com.example.TrocaDeIdioma.model.Response.UserChatResponse;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
  private UserChatResponse sender;
  private UserChatResponse receiver;
  private String message;
  private String date;
  private StatusChat status;

  private String userTipo;
}
