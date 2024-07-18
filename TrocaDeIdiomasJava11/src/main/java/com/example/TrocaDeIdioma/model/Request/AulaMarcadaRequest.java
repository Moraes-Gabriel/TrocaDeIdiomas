package com.example.TrocaDeIdioma.model.Request;


import com.example.TrocaDeIdioma.model.User;

import java.time.LocalDateTime;


public class AulaMarcadaRequest {
  private User user;
  private User partner;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getPartner() {
    return partner;
  }

  public void setPartner(User partner) {
    this.partner = partner;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }
}
