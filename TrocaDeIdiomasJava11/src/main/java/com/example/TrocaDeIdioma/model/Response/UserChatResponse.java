package com.example.TrocaDeIdioma.model.Response;

import lombok.Data;

@Data
public class UserChatResponse {


  private long id;
  private String firstName;
  private String lastName;

  public UserChatResponse(long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
