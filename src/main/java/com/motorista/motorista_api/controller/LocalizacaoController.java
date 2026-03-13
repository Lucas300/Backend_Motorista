package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.motorista.motorista_api.model.Localizacao;
import com.motorista.motorista_api.service.LocalizacaoService;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @PostMapping("/{viagemId}")
    public ResponseEntity<Localizacao> salvar(
            @PathVariable("viagemId") Long viagemId,
            @RequestBody Localizacao localizacao) {

        Localizacao salva = localizacaoService.salvar(viagemId, localizacao);
        return ResponseEntity.ok(salva);
    }

    @GetMapping("/{viagemId}")
    public ResponseEntity<List<Localizacao>> listarPorViagem(
            @PathVariable("viagemId") Long viagemId) {

        return ResponseEntity.ok(localizacaoService.listarPorViagem(viagemId));
    }
    
    @GetMapping("/distancia/{viagemId}")
    public ResponseEntity<Double> calcularDistancia(@PathVariable Long viagemId) {

        double distancia = localizacaoService.calcularDistanciaTotal(viagemId);

        return ResponseEntity.ok(distancia);
    }
}