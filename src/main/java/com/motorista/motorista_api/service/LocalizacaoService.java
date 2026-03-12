package com.motorista.motorista_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Localizacao;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.LocalizacaoRepository;
import com.motorista.motorista_api.repository.ViagemRepository;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;
    private final ViagemRepository viagemRepository;

    public LocalizacaoService(LocalizacaoRepository localizacaoRepository,
                              ViagemRepository viagemRepository) {
        this.localizacaoRepository = localizacaoRepository;
        this.viagemRepository = viagemRepository;
    }

    public Localizacao salvar(Long viagemId, Localizacao localizacao) {

        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        localizacao.setViagem(viagem);
        localizacao.setDataHora(LocalDateTime.now());

        return localizacaoRepository.save(localizacao);
    }

    public List<Localizacao> listarPorViagem(Long viagemId) {
        return localizacaoRepository.findByViagemIdOrderByDataHoraAsc(viagemId);
    }
}