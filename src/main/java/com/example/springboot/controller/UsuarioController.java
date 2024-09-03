package com.example.springboot.controller;

import com.example.springboot.model.Usuario;
import com.example.springboot.service.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
public class UsuarioController {
    private Map<Long, Boolean> verificaAtualizacao;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> getAllModels() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/hello")
    public String buscarProdutos(@RequestParam(name = "nome") String nome) {
        // Lógica para buscar produtos pelo nome
        usuarioService.teste(nome);
        return "dsdsdsds";
    }

    //@GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario newUsuario) {
        Usuario updatedUsuario = usuarioService.updateUsuario(id, newUsuario);
        return updatedUsuario != null ? ResponseEntity.ok(updatedUsuario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/inserirLista")
    public ResponseEntity<String> saveAllUsuario(@RequestBody List<Usuario> usuarios) {
        try{
            usuarioService.saveAllUsuarios(usuarios);
            return ResponseEntity.status(HttpStatus.CREATED).body("Inseridos " + usuarios.size() + ".");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(e.getMessage());
        }
    }
   
}