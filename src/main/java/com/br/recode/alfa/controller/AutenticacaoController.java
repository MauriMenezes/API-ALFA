package com.br.recode.alfa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.recode.alfa.config.security.TokenService;
import com.br.recode.alfa.controller.dto.UsuarioDTO;
import com.br.recode.alfa.controller.dto.TokenDTO;
import com.br.recode.alfa.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {

    UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(form.getCpf(),
        form.getSenha());
    try {

      Authentication authentication = authManager.authenticate(dadosLogin);
      String token = tokenService.gerarToken(authentication);
      System.out.println("entrou aqui");
      return ResponseEntity.ok(new TokenDTO(token, "Bearer", authentication));

    } catch (Exception e) {

      System.out.println("dando ruim aqui");
      return ResponseEntity.badRequest().build();
    }

  }
}
