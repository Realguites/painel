package com.example.springboot.controller;

import com.example.springboot.model.Usuario;
import com.example.springboot.service.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
public class UsuarioController {
    private Map<Long, Boolean> verificaAtualizacao;

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

    @GetMapping("/stream-sse")
    public SseEmitter streamSse() {
        SseEmitter emitter = new SseEmitter();

        new Thread(() -> {
            try {   
                for (int i = 0; i < 10; i++) {
                    emitter.send("Atualização #" + i);
                    Thread.sleep(1000); // Simula um evento a cada segundo
                }
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}