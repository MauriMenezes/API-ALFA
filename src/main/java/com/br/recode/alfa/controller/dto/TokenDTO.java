package com.br.recode.alfa.controller.dto;

import org.springframework.security.core.Authentication;

import com.br.recode.alfa.model.Usuario;

public class TokenDTO {

  private Long idUser;
  private String emailUser;

  private String token;
  private String tipo;

  public TokenDTO(String token, String tipo, Authentication authentication) {
    Usuario logado = (Usuario) authentication.getPrincipal();
    this.token = token;
    this.tipo = tipo;

    this.idUser = logado.getId();
    this.emailUser = logado.getEmail();

  }

  public String getToken() {
    return this.token;
  }

  public String getTipo() {
    return this.tipo;
  }

  public Long getIdUser() {
    return this.idUser;
  }

  public String getEmailUser() {
    return this.emailUser;
  }

}
