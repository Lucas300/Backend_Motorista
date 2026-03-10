package com.motorista.motorista_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Localizacao;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.LocalizacaoRepository;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;

    // Construtor manual para injeção pelo Spring
    public LocalizacaoService(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    // Salva um ponto de GPS
    public Localizacao salvar(Localizacao localizacao) {
        return localizacaoRepository.save(localizacao);
    }

    // Busca todos os pontos de uma viagem, ordenados por data/hora
    public List<Localizacao> buscarPorViagem(Viagem viagem) {
        return localizacaoRepository.findByViagemOrderByDataHoraAsc(viagem);
    }

    // Aqui depois podemos adicionar:
    // - detectar paradas
    // - excesso de velocidade
    // - preparar dados para integração com Google Maps
}