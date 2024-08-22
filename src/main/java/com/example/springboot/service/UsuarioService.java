package com.example.springboot.service;

import com.example.springboot.model.Usuario;
import com.example.springboot.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllModels() {
        return usuarioRepository.findAll();

    }
}