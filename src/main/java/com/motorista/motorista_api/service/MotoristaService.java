package com.motorista.motorista_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Motorista;
import com.motorista.motorista_api.repository.MotoristaRepository;

@Service
public class MotoristaService {

    private final MotoristaRepository repository;

    public MotoristaService(MotoristaRepository repository) {
        this.repository = repository;
    }

    public Motorista salvar(Motorista motorista){
        return repository.save(motorista);
    }

    public List<Motorista> listar(){
        return repository.findAll();
    }

}