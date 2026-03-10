package com.motorista.motorista_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Veiculo;
import com.motorista.motorista_api.repository.VeiculoRepository;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public Veiculo salvar(Veiculo veiculo){
        return repository.save(veiculo);
    }

    public List<Veiculo> listar(){
        return repository.findAll();
    }
}
