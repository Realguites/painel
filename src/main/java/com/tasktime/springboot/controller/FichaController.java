package com.tasktime.springboot.controller;

import com.tasktime.springboot.model.Ficha;
import com.tasktime.springboot.model.Smartphone;
import com.tasktime.springboot.service.FichaService;
import com.tasktime.springboot.service.SmartphoneService;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @Autowired
    private SmartphoneService smartphoneService;

    @GetMapping("/heleeeelo")
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/streameeeee-sse")
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

    @PostMapping("/{idDispositivo}")
    public ResponseEntity<Ficha> createFicha(@PathVariable("idDispositivo") String idDispositivo, @RequestBody Ficha ficha) {
        Smartphone smartphone = smartphoneService.getSmartphoneByIdIdentificacao(idDispositivo);
        if(smartphone == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else{
            if(!smartphone.isAtivo()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }else{
                Ficha fichaBase = fichaService.buscarUltimaFichaSalva(smartphone.getEmpresa(), ficha.getIdentPrioridade(), new java.sql.Date(new Date().getTime()));
                if(fichaBase == null){ //primeira ficha do dia
                    ficha.setNumero(1L);
                }else{
                    ficha.setNumero(fichaBase.getNumero() + 1);
                }
                ficha.setDataCadastro(new Date());
                ficha.setHorarioCriacao(new java.sql.Date(ficha.getDataCadastro().getTime()));
                ficha.setEmpresa(smartphone.getEmpresa());
                Ficha savedFicha = fichaService.saveFicha(ficha);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedFicha);
            }
        }
    }
}