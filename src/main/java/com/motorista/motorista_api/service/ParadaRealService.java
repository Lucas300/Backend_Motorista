package com.motorista.motorista_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.ParadaReal;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.ParadaRealRepository;
import com.motorista.motorista_api.repository.ViagemRepository;

@Service
public class ParadaRealService {

    private final ParadaRealRepository paradaRealRepository;
    private final ViagemRepository viagemRepository;

    public ParadaRealService(ParadaRealRepository paradaRealRepository,
                             ViagemRepository viagemRepository) {

        this.paradaRealRepository = paradaRealRepository;
        this.viagemRepository = viagemRepository;
    }

    public ParadaReal registrar(Long viagemId, ParadaReal parada) {

        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        parada.setViagem(viagem);
        parada.setDataHora(LocalDateTime.now());

        return paradaRealRepository.save(parada);
    }

    public List<ParadaReal> listar(Long viagemId) {
        return paradaRealRepository.findByViagemId(viagemId);
    }
}