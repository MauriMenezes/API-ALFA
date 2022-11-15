package com.br.recode.alfa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String cpf;
  private String nome;
  private String email;
  private String senha;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Perfil> perfis = new ArrayList<>();

  public Usuario() {
  }

  public Usuario(String CPF, String nome, String Email, String senha) {

    this.cpf = CPF;
    this.nome = nome;
    this.email = Email;
    this.senha = senha;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCPF() {
    return this.cpf;
  }

  public void setCPF(String CPF) {
    this.cpf = CPF;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String Email) {
    this.email = Email;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Usuario id(long id) {
    setId(id);
    return this;
  }

  public Usuario CPF(String CPF) {
    setCPF(CPF);
    return this;
  }

  public Usuario nome(String nome) {
    setNome(nome);
    return this;
  }

  public Usuario Email(String Email) {
    setEmail(Email);
    return this;
  }

  public Usuario senha(String senha) {
    setSenha(senha);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Usuario)) {
      return false;
    }
    Usuario cliente = (Usuario) o;
    return id == cliente.id && Objects.equals(cpf, cliente.cpf) && Objects.equals(nome, cliente.nome)
        && Objects.equals(email, cliente.email) && Objects.equals(senha, cliente.senha);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cpf, nome, email, senha);
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", CPF='" + getCPF() + "'" +
        ", nome='" + getNome() + "'" +
        ", Email='" + getEmail() + "'" +
        ", senha='" + getSenha() + "'" +
        "}";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.perfis;
  }

  @Override
  public String getPassword() {
    return this.senha;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
