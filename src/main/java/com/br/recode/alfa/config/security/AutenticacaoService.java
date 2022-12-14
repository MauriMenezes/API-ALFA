package com.br.recode.alfa.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.recode.alfa.model.Usuario;
import com.br.recode.alfa.repository.ClienteRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Usuario> cliente = clienteRepository.findByCpf(username);

    if (cliente.isPresent()) {
      System.out.println("ACHOu");
      return cliente.get();
    }

    throw new UsernameNotFoundException("DADOS INVALIDOS");

  }

}
