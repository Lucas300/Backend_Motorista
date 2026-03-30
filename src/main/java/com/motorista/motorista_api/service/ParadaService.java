package com.motorista.motorista_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Parada;
import com.motorista.motorista_api.repository.ParadaRepository;

@Service
public class ParadaService {

    private final ParadaRepository paradaRepository;

    public ParadaService(ParadaRepository paradaRepository) {
        this.paradaRepository = paradaRepository;
    }

    // Salvar parada
    public Parada salvar(Parada parada) {
        return paradaRepository.save(parada);
    }

    // Listar todas
    public List<Parada> listarTodas() {
        return paradaRepository.findAll();
    }
    
 // Deletar parada por ID
    public void deletar(Long id) {
        if (!paradaRepository.existsById(id)) {
            throw new RuntimeException("Parada não encontrada com ID: " + id);
        }
        paradaRepository.deleteById(id);
    }
}