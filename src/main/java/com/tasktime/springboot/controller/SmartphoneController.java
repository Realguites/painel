package com.tasktime.springboot.controller;

import com.tasktime.springboot.model.Smartphone;
import com.tasktime.springboot.service.SmartphoneService;

import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/smartphones")
public class SmartphoneController {

    @Autowired
    private SmartphoneService smartphoneService;

    @GetMapping
    public List<Smartphone> getAllModels() {
        return smartphoneService.getAllSmartphones();
    }

    //@GetMapping("/{id}")
    public ResponseEntity<Optional<Smartphone>> getSmartphoneById(@PathVariable Long id) {
        Optional<Smartphone> smartphone = smartphoneService.getSmartphoneById(id);
        return smartphone != null ? ResponseEntity.ok(smartphone) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Smartphone> saveSmartphone(@RequestBody Smartphone smartphone) {
        Smartphone smartphoneBase = smartphoneService.getSmartphoneByIdIdentificacao(smartphone.getNrIdentificacao());
        if(smartphoneBase == null){
            smartphone.setDataCadastro(new Date());
            smartphone.setAtivo(false);
            Smartphone savedSmartphone = smartphoneService.saveSmartphone(smartphone);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSmartphone);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(smartphoneBase);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Smartphone> updateSmartphone(@PathVariable Long id, @RequestBody Smartphone newSmartphone) {
        newSmartphone.setDataAlteracao(new Date());
        Smartphone updatedSmartphone = smartphoneService.updateSmartphone(id, newSmartphone);
        return updatedSmartphone != null ? ResponseEntity.ok(updatedSmartphone) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmartphone(@PathVariable Long id) {
        smartphoneService.deleteSmartphone(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/inserirLista")
    public ResponseEntity<String> saveAllSmartphone(@RequestBody List<Smartphone> smartphones) {
        try{
            smartphoneService.saveAllSmartphones(smartphones);
            return ResponseEntity.status(HttpStatus.CREATED).body("Inseridos " + smartphones.size() + ".");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(e.getMessage());
        }
    }
   
}