package com.br.recode.alfa.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.br.recode.alfa.model.Usuario;

public class UsuarioDTO {

  private long id;
  private String CPF;
  private String nome;
  private String Email;
  private String senha;

  public UsuarioDTO(Usuario cliente) {

    this.id = cliente.getId();
    this.CPF = cliente.getCPF();
    this.nome = cliente.getNome();
    this.Email = cliente.getEmail();
    this.senha = cliente.getSenha();
  }

  public UsuarioDTO() {

  }

  public long getId() {
    return this.id;
  }

  public String getCPF() {
    return this.CPF;
  }

  public String getNome() {
    return this.nome;
  }

  public String getEmail() {
    return this.Email;
  }

  public String getSenha() {
    return this.senha;
  }

  public static List<UsuarioDTO> converter(List<Usuario> cliente) {
    return cliente.stream().map(UsuarioDTO::new).collect(Collectors.toList());
  }

}
