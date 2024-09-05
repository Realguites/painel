package com.example.springboot.service;

import com.example.springboot.model.Empresa;
import com.example.springboot.repository.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> getEmpresaById(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa saveEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void saveAllEmpresas(List<Empresa> empresas) {
        empresaRepository.saveAll(empresas);
    }

    public Empresa updateEmpresa(Long id, Empresa updatedEmpresa) {
        Optional<Empresa> existingEmpresa = empresaRepository.findById(id);
        if (existingEmpresa.isPresent()) {
            //existingEmpresa.get().setNome(updatedEmpresa.getNome());
           // existingEmpresa.get().setEmail(updatedEmpresa.getEmail());

            return empresaRepository.save(existingEmpresa.get());
        }
        return null;
    }

    public List<Empresa> buscarPorRazaoSocial(String rs) {
        return empresaRepository.findByRazaoSocialLikeIgnoreCase(rs);
    }

    public List<Empresa> buscarPorCnpj(String cnpj) {
        return empresaRepository.findByCnpjLikeIgnoreCase(cnpj);
    }

    public void deleteEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

}