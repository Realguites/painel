package com.example.springboot.controller;

import com.example.springboot.model.Usuario;
import com.example.springboot.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/usuarios")
    public List<Usuario> getAllModels() {
        return usuarioService.getAllModels();
    }
}