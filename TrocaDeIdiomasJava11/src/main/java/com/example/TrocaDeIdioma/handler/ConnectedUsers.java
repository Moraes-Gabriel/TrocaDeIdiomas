package com.example.TrocaDeIdioma.handler;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectedUsers {

  private static final Map<Long, WebSocketSession> connectedUsers = new ConcurrentHashMap<>();

  public static void add(Long userId, WebSocketSession session) {
    connectedUsers.put(userId, session);
  }

  public static void remove(Long userId, WebSocketSession session) {
    connectedUsers.remove(userId, session);
  }

  public static Map<Long, WebSocketSession> getConnectedUsers() {
    return connectedUsers;
  }
}
