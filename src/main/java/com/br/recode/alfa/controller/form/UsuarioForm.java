package com.br.recode.alfa.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.recode.alfa.model.Usuario;

public class UsuarioForm {

  private String nome;
  @NotNull
  @NotEmpty
  private String cpf;

  private String email;
  @NotNull
  @NotEmpty
  private String senha;

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Usuario converter(BCryptPasswordEncoder encoder) {

    this.setSenha(encoder.encode(senha));
    return new Usuario(cpf, nome, email, senha);
  }

}
