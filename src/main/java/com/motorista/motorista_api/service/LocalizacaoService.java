package com.motorista.motorista_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Localizacao;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.LocalizacaoRepository;
import com.motorista.motorista_api.repository.ViagemRepository;
import com.motorista.motorista_api.utils.GeoUtils;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;
    private final ViagemRepository viagemRepository;

    public LocalizacaoService(LocalizacaoRepository localizacaoRepository,
                              ViagemRepository viagemRepository) {
        this.localizacaoRepository = localizacaoRepository;
        this.viagemRepository = viagemRepository;
    }

    // salvar ponto GPS
    public Localizacao salvar(Long viagemId, Localizacao localizacao) {

        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        localizacao.setViagem(viagem);
        localizacao.setDataHora(LocalDateTime.now());

        return localizacaoRepository.save(localizacao);
    }

    // listar pontos da viagem
    public List<Localizacao> listarPorViagem(Long viagemId) {
        return localizacaoRepository.findByViagemIdOrderByDataHoraAsc(viagemId);
    }

    // calcular distancia total percorrida
    public double calcularDistanciaTotal(Long viagemId) {

        List<Localizacao> pontos =
                localizacaoRepository.findByViagemIdOrderByDataHoraAsc(viagemId);

        if (pontos.size() < 2) {
            return 0;
        }

        double distanciaTotal = 0;

        for (int i = 1; i < pontos.size(); i++) {

            Localizacao p1 = pontos.get(i - 1);
            Localizacao p2 = pontos.get(i);

            distanciaTotal += GeoUtils.calcularDistancia(
                    p1.getLatitude().doubleValue(),
                    p1.getLongitude().doubleValue(),
                    p2.getLatitude().doubleValue(),
                    p2.getLongitude().doubleValue()
            );
        }

        return distanciaTotal;
    }
}