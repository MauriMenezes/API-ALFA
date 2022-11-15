package com.br.recode.alfa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.recode.alfa.controller.dto.UsuarioDTO;
import com.br.recode.alfa.controller.form.UsuarioForm;
import com.br.recode.alfa.model.Usuario;
import com.br.recode.alfa.repository.ClienteRepository;

@Controller

@ResponseBody
@RequestMapping("/")
public class ClienteController {

  @Autowired
  private ClienteRepository clienteRepository;

  @GetMapping("/cliente")

  public String hello() {
    return "OLÁ";
  }

  @GetMapping("/listar")
  public List<UsuarioDTO> lista() {
    List<Usuario> cli = clienteRepository.findAll();
    return UsuarioDTO.converter(cli);
  }

  @GetMapping("/listar/{id}")
  public UsuarioDTO detalhar(@PathVariable Long id) {
    Usuario cliente = clienteRepository.getReferenceById(id);
    return new UsuarioDTO(cliente);
  }

  @DeleteMapping("/remove/{id}")
  public ResponseEntity<?> remove(@PathVariable Long id) {

    clienteRepository.deleteById(id);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {

    Optional<Usuario> cli = clienteRepository.findByCpf(form.getCpf());
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    if (cli.isPresent()) {
      System.out.println("já existe no banco");
      return ResponseEntity.badRequest().build();

    } else {
      System.out.println("não  existe no banco");
      Usuario cliente = form.converter(encoder);
      clienteRepository.save(cliente);

      URI uri = uriBuilder.path("/agenda/cadastrar/{id}").buildAndExpand(cliente.getId()).toUri();
      return ResponseEntity.created(uri).body(new UsuarioDTO(cliente));
    }

  }
}
