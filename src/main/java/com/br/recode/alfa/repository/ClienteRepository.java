package com.br.recode.alfa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.recode.alfa.model.Usuario;

public interface ClienteRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByEmail(String email);

  Optional<Usuario> findByCpf(String cpf);

}
