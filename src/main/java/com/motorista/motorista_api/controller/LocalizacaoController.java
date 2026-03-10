package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.motorista.motorista_api.model.Localizacao;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.ViagemRepository;
import com.motorista.motorista_api.service.LocalizacaoService;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @Autowired
    private ViagemRepository viagemRepository;

    @PostMapping
    public ResponseEntity<Localizacao> salvar(@RequestBody Localizacao localizacao) {
        Viagem viagem = viagemRepository.findById(localizacao.getViagem().getId())
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
        localizacao.setViagem(viagem);

        Localizacao salva = localizacaoService.salvar(localizacao);
        return ResponseEntity.ok(salva);
    }

    @GetMapping("/{viagemId}")
    public ResponseEntity<List<Localizacao>> listarPorViagem(@PathVariable Long viagemId) {
        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
        List<Localizacao> localizacoes = localizacaoService.buscarPorViagem(viagem);
        return ResponseEntity.ok(localizacoes);
    }
}