package com.tasktime.springboot.controller;

import com.tasktime.springboot.model.Empresa;
import com.tasktime.springboot.service.EmpresaService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<Empresa> getAllModels() {
        return empresaService.getAllEmpresas();
    }

    //@GetMapping("/{id}")
    public ResponseEntity<Optional<Empresa>> getEmpresaById(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.getEmpresaById(id);
        return empresa != null ? ResponseEntity.ok(empresa) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Empresa> saveEmpresa(@RequestBody Empresa empresa) {
        empresa.setDataCadastro(new Date());
        Empresa savedEmpresa = empresaService.saveEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable("id") Long id, @RequestBody Empresa newEmpresa) {
        newEmpresa.setDataAlteracao(new Date());
        Empresa updatedEmpresa = empresaService.updateEmpresa(id, newEmpresa);
        return updatedEmpresa != null ? ResponseEntity.ok(updatedEmpresa) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable("id") Long id) {
        empresaService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/inserirLista")
    public ResponseEntity<String> saveAllEmpresa(@RequestBody List<Empresa> empresas) {
        try{
            empresaService.saveAllEmpresas(empresas);
            return ResponseEntity.status(HttpStatus.CREATED).body("Inseridos " + empresas.size() + ".");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(e.getMessage());
        }
    }
   
}