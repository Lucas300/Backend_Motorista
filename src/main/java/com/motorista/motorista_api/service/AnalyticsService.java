package com.motorista.motorista_api.service;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.ViagemRepository;

@Service
public class AnalyticsService {

    private final ViagemRepository viagemRepository;
    private final LocalizacaoService localizacaoService;

    public AnalyticsService(
            ViagemRepository viagemRepository,
            LocalizacaoService localizacaoService) {

        this.viagemRepository = viagemRepository;
        this.localizacaoService = localizacaoService;
    }

    public double calcularKmOcioso(Long viagemId) {

        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        double kmReal = localizacaoService.calcularDistanciaTotal(viagemId);

        double kmPlanejado = viagem.getLinha().getKmPlanejado();

        double kmOcioso = kmReal - kmPlanejado;

        return Math.max(kmOcioso, 0);
    }
}