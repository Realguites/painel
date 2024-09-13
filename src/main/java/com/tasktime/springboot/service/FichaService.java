package com.tasktime.springboot.service;

import com.tasktime.springboot.enums.IdentificacaoPrioridade;
import com.tasktime.springboot.model.Empresa;
import com.tasktime.springboot.model.Ficha;
import com.tasktime.springboot.repository.FichaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    public List<Ficha> getAllFichas() {
        return fichaRepository.findAll();
    }

    public Optional<Ficha> getFichaById(Long id) {
        return fichaRepository.findById(id);
    }

    public Ficha saveFicha(Ficha ficha) {

        return fichaRepository.save(ficha);
    }

    public void saveAllFichas(List<Ficha> fichas) {
        fichaRepository.saveAll(fichas);
    }

    public Ficha updateFicha(Long id, Ficha updatedFicha) {
        Optional<Ficha> existingFicha = fichaRepository.findById(id);
        if (existingFicha.isPresent()) {
            //existingFicha.get().setNome(updatedFicha.getNome());
           // existingFicha.get().setEmail(updatedFicha.getEmail());

            return fichaRepository.save(existingFicha.get());
        }
        return null;
    }

    public Ficha buscarUltimaFichaSalva(Empresa empresa, IdentificacaoPrioridade getIdentPrioridade, Date data) {
        return fichaRepository.findByEmpresaIdentificacaoPrioridadeDataCriacao(empresa, getIdentPrioridade, data, data);
    }

    public void deleteFicha(Long id) {
        fichaRepository.deleteById(id);
    }

}