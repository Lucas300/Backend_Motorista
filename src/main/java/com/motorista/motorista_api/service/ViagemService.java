package com.motorista.motorista_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.ViagemRepository;

@Service
public class ViagemService {

    private final ViagemRepository repository;

    public ViagemService(ViagemRepository repository) {
        this.repository = repository;
    }

    public Viagem salvar(Viagem viagem){
        return repository.save(viagem);
    }

    public List<Viagem> listar(){
        return repository.findAll();
    }
}