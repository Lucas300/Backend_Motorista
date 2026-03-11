package com.motorista.motorista_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Linha;
import com.motorista.motorista_api.repository.LinhaRepository;

@Service
public class LinhaService {

    private final LinhaRepository linhaRepository;

    public LinhaService(LinhaRepository linhaRepository) {
        this.linhaRepository = linhaRepository;
    }

    // Listar todas as linhas
    public List<Linha> listarTodas() {
        return linhaRepository.findAll();
    }

    // Buscar linha por ID
    public Linha buscarPorId(Long id) {
        return linhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Linha não encontrada"));
    }

    // Salvar linha
    public Linha salvar(Linha linha) {
        return linhaRepository.save(linha);
    }
}