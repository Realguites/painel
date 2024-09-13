package com.tasktime.springboot.service;

import com.tasktime.springboot.model.Smartphone;
import com.tasktime.springboot.repository.SmartphoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmartphoneService {

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    public List<Smartphone> getAllSmartphones() {
        return smartphoneRepository.findAll();
    }

    public Optional<Smartphone> getSmartphoneById(Long id) {
        return smartphoneRepository.findById(id);
    }

    public Smartphone saveSmartphone(Smartphone smartphone) {
        return smartphoneRepository.save(smartphone);
    }

    public void saveAllSmartphones(List<Smartphone> smartphones) {
        smartphoneRepository.saveAll(smartphones);
    }
    public Smartphone getSmartphoneByIdIdentificacao(String identificacao){
        return smartphoneRepository.findBynrIdenSmartphone(identificacao);
    }

    public Smartphone updateSmartphone(Long id, Smartphone updatedSmartphone) {
        Optional<Smartphone> existingSmartphone = smartphoneRepository.findById(id);
        if (existingSmartphone.isPresent()) {
            //existingSmartphone.get().setNome(updatedSmartphone.getNome());
           // existingSmartphone.get().setEmail(updatedSmartphone.getEmail());

            return smartphoneRepository.save(existingSmartphone.get());
        }
        return null;
    }

    public void deleteSmartphone(Long id) {
        smartphoneRepository.deleteById(id);
    }

}